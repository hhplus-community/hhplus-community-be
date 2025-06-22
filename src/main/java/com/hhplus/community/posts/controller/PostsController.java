package com.hhplus.community.posts.controller;

import com.hhplus.community.posts.dto.response.PostResponse;
import com.hhplus.community.posts.dto.request.PostWriteRequest;
import com.hhplus.community.posts.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostsService postsService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostWriteRequest request) {
        try {
            PostResponse response = postsService.createPost(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new com.hhplus.community.posts.dto.response.ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new com.hhplus.community.posts.dto.response.ErrorResponse("서버 오류가 발생했습니다."));
        }
    }
}
