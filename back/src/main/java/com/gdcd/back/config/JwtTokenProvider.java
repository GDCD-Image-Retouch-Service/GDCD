package com.gdcd.back.config;

import com.gdcd.back.domain.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private String SALT = "projectSSAFY";

    private long tokenValidTime = 300 * 60 * 1000L;
    private final UserRepository userRepository;

    @PostConstruct
    protected void init() {
        SALT = Base64.getEncoder().encodeToString(SALT.getBytes());
    }

    public String createToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, SALT)
                .compact();
    }

    public Authentication getAuthentication(String token) { //response token
        UserDetails userDetails = (UserDetails) userRepository.findByEmail(this.getUserPk(token))
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(SALT).parseClaimsJwt(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SALT).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String decodeToken(String jwtToken) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(SALT).parseClaimsJws(jwtToken);
        return claims.getBody().get("sub").toString();
    }
}
