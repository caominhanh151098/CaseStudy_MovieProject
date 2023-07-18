package com.example.casestudy_movieproject.service.movie.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowMovieDetail {
    private String name;
    private String airedDate;
    private String scoreIMDb;
    private String duration;
    private String quality;
    private String description;
    private String img;
    private String movieGenres;
    private String directors;
    private String actors;
}
