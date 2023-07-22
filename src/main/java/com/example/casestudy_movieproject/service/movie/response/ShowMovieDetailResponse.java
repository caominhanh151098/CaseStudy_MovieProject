package com.example.casestudy_movieproject.service.movie.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowMovieDetailResponse {
    private String id;
    private String name;
    private String airedYear;
    private String scoreIMDb;
    private String duration;
    private String quality;
    private String description;
    private String img_movie;
    private String movieGenres;
    private String directors;
    private String actors;
    private String commentNum;
    private String status;
    private String type;
    private String totalEp;
}
