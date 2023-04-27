package com.example.instagram.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostLikeDislikeDtoRequest {
    
    private Boolean isLike;
    
    private Long postId;
    
    private Long userId;
    
    private LocalDateTime localDateTime;
}
