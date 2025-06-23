package com.hhplus.community.posts.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostWriteRequest {
    private String title;
    private String content;
}
