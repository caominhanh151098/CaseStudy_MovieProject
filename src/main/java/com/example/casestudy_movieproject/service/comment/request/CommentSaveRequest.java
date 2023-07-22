package com.example.casestudy_movieproject.service.comment.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentSaveRequest {
    private String movieId;
    private String userId;
    private String content;
}
