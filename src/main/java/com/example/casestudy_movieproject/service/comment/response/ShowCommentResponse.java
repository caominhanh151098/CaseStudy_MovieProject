package com.example.casestudy_movieproject.service.comment.response;

import com.example.casestudy_movieproject.service.user.response.ShowUserCommentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowCommentResponse {
    private String content;
    private ShowUserCommentResponse user;
    private String timeComment;
}
