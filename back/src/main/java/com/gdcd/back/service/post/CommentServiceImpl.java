package com.gdcd.back.service.post;

import com.gdcd.back.config.JwtTokenProvider;
import com.gdcd.back.domain.comment.Comment;
import com.gdcd.back.domain.comment.CommentRepository;
import com.gdcd.back.domain.user.UserRepository;
import com.gdcd.back.domain.user.UserSimple;
import com.gdcd.back.dto.post.request.CommentCreateRequestDto;
import com.gdcd.back.dto.post.request.CommentUpdateRequestDto;
import com.gdcd.back.dto.post.response.CommentKidResponseDto;
import com.gdcd.back.dto.post.response.CommentUpperResponseDto;
import com.sun.org.apache.regexp.internal.RE;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private Map<String, Object> RESULT_OBJECT;

    @Override
    public Map<String, Object> findComments(String token, Long postId) {
        RESULT_OBJECT = new HashMap<>();
        try {
            List<CommentUpperResponseDto> list = new ArrayList<>();
            List<CommentKidResponseDto> kidList = new ArrayList<>();

            List<Comment> UpperDocumentList = commentRepository.findAllByPostIdAndUpper(postId, 0L);
            for (Comment comment : UpperDocumentList) {
                List<Comment> KidDocumentList = commentRepository.findAllByUpper(comment.getId());
                for (Comment kid : KidDocumentList) {
                    kidList.add(new CommentKidResponseDto(kid));
                }
                list.add(new CommentUpperResponseDto(comment, kidList));
            }
            RESULT_OBJECT.put("comments", list);
        } catch (Exception e) {
            RESULT_OBJECT.put("error", "COMMENTS NOT FOUND");
        }
        return RESULT_OBJECT;
    }

    @Override
    public Map<String, Object> addComment(String token, CommentCreateRequestDto requestDto) {
        RESULT_OBJECT = new HashMap<>();
        try {
            RESULT_OBJECT.put("comments", commentRepository.save(requestDto.toDocument(findUserByToken(token))).getId());
        } catch (Exception e) {
            e.printStackTrace();
            RESULT_OBJECT.put("error", "COMMENTS NOT ADDED");
        }
        return RESULT_OBJECT;
    }

    @Override
    public Map<String, Object> modifyComment(String token, CommentUpdateRequestDto requestDto) {
        RESULT_OBJECT = new HashMap<>();
        try {

        } catch (Exception e) {
            RESULT_OBJECT.put("error", "COMMENTS NOT FOUND");
        }
        return RESULT_OBJECT;
    }

    @Override
    public Map<String, Object> deleteComment(String token, Long commentId) {
        RESULT_OBJECT = new HashMap<>();
        try {

        } catch (Exception e) {
            RESULT_OBJECT.put("error", "COMMENTS NOT FOUND");
        }
        return RESULT_OBJECT;
    }

    private UserSimple findUserByToken(String token) throws Exception{
        if (userRepository.findByEmail(jwtTokenProvider.decodeToken(token)).isPresent()) {
            return userRepository.findByEmail(jwtTokenProvider.decodeToken(token)).get().simplify();
        } else {
            throw new Exception("USER NOT FOUND");
        }
    }
}
