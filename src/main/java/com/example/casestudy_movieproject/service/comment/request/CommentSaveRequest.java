package com.example.casestudy_movieproject.service.comment.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentSaveRequest {
    private String movieId;
    @NotBlank
    private String content;
}
