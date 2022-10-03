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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private Map<String, Object> RESULT_OBJECT;

    @Override
    public Map<String, Object> findComments(String token, Long postId, Pageable pageable) {
        RESULT_OBJECT = new HashMap<>();
        try {
            List<CommentUpperResponseDto> list = new ArrayList<>();
            List<CommentKidResponseDto> kidList;

//            List<Comment> UpperDocumentList = commentRepository.findAllByPostIdAndUpperAndValidation(postId, 0L, true);
            List<Comment> UpperDocumentList = commentRepository.findAllByPostIdAndUpperAndValidationOrderByRegistDateDesc(postId, 0L, true, pageable);
            for (Comment comment : UpperDocumentList) {
                kidList = new ArrayList<>();
//                List<Comment> KidDocumentList = commentRepository.findAllByUpperAndValidation(comment.getId(), true);
                List<Comment> KidDocumentList = commentRepository.findAllByUpperAndValidationOrderByRegistDateDesc(comment.getId(), true, pageable);

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
            Comment comment = findCommentById(requestDto.getCommentId());
            comment.update(requestDto);
            RESULT_OBJECT.put("commentId", commentRepository.save(comment).getId());
        } catch (Exception e) {
            RESULT_OBJECT.put("error", "COMMENTS NOT FOUND");
        }
        return RESULT_OBJECT;
    }

    @Override
    public Map<String, Object> deleteComment(String token, Long commentId) {
        RESULT_OBJECT = new HashMap<>();
        try {
            Comment comment = findCommentById(commentId);
            List<Comment> kidList = commentRepository.findAllByUpperAndValidation(commentId, true);
            for (Comment kid : kidList) {
                kid.delete();
                commentRepository.save(kid);
            }
            comment.delete();
            RESULT_OBJECT.put("commentId", commentRepository.save(comment).getId());
        } catch (Exception e) {
            RESULT_OBJECT.put("error", "COMMENTS NOT FOUND");
        }
        return RESULT_OBJECT;
    }

    private Comment findCommentById(Long commentId) throws Exception {
        if (commentRepository.findById(commentId).isPresent())
            return commentRepository.findById(commentId).get();
        else
            throw new Exception("COMMENT NOT FOUND");
    }

    private UserSimple findUserByToken(String token) throws Exception{
        if (userRepository.findByEmail(jwtTokenProvider.decodeToken(token)).isPresent()) {
            return userRepository.findByEmail(jwtTokenProvider.decodeToken(token)).get().simplify();
        } else {
            throw new Exception("USER NOT FOUND");
        }
    }
}
