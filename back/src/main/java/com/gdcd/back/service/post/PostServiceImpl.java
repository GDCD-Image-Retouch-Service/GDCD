package com.gdcd.back.service.post;

import com.gdcd.back.dto.post.request.PostCreateRequestDto;
import com.gdcd.back.dto.post.request.PostReportRequestDto;
import com.gdcd.back.dto.post.request.PostUpdateRequestDto;
import com.gdcd.back.dto.post.response.PostDetailResponseDto;
import com.gdcd.back.dto.post.response.PostListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    public List<PostListResponseDto> findPosts(){
        List<PostListResponseDto> list = new ArrayList<>();
        list.add(new PostListResponseDto());
        return list;
    }

    public PostDetailResponseDto findPostById(Long postId){
        PostDetailResponseDto responseDto = new PostDetailResponseDto();
        List<String> list = new ArrayList<>();
        list.add("사진 1");
        list.add("사진 2");
        responseDto.setImages(list);
        List<String> object = new ArrayList<>();
        object.add("object detection 1");
        object.add("object detection 2");
        responseDto.setObjects(object);

        return responseDto;
    }

    public PostCreateRequestDto addPost(PostCreateRequestDto requestDto){
        return requestDto;
    };

    public PostUpdateRequestDto modifyPost(PostUpdateRequestDto requestDto){
        return requestDto;
    }

    public Long removePost(Long postId){
        return postId;
    }

    public PostReportRequestDto reportPost(PostReportRequestDto requestDto){
        return requestDto;
    }

    public Long likePost(Long postId){
        return postId;
    }

    public Long scrapPost(Long postId){
        return postId;
    }


}
