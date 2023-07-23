package com.example.casestudy_movieproject.service.vote.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoteSaveRequest {
    private String score;
    private String userId;
    private String movieId;
}
