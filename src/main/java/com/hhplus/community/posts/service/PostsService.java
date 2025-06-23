package com.hhplus.community.posts.service;

import com.hhplus.community.posts.dto.response.PostResponse;
import com.hhplus.community.posts.entity.Post;
import com.hhplus.community.posts.dto.request.PostWriteRequest;
import com.hhplus.community.posts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class PostsService {
    @Autowired
    private PostRepository postRepository;

    public PostResponse createPost(PostWriteRequest request) {
        // 입력 검증
        validatePostRequest(request);

        // 인증된 사용자명 추출
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Entity 생성
        Post post = new Post(
                request.getTitle(),
                username,
                request.getContent()
        );

        // 저장
        Post savedPost = postRepository.save(post);

        // DTO로 변환하여 반환
        return new PostResponse(savedPost);
    }

    private void validatePostRequest(PostWriteRequest request) {
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("제목은 필수입니다.");
        }
        if (request.getContent() == null || request.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("내용은 필수입니다.");
        }

        // 길이 검증
        if (request.getTitle().length() > 200) {
            throw new IllegalArgumentException("제목은 200자를 초과할 수 없습니다.");
        }
    }
}
