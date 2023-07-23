package com.example.casestudy_movieproject.service.movie.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieListResponse {

    private int id;
    private String name;
    private String airedYear;
    private String scoreIMDb;
    private String duration;
    private String quality;
    private String description;
    private String url;
    private String urlTrailer;
    private String img_poster;
    private String img_movie;
    private String movieGenres;
    private String directors;
    private String actors;
    private String status;
    private String eKips;
    private String type;
    public MovieListResponse(String name,String movieGenres){
        this.name = name;
        this.movieGenres = movieGenres;
    }
}
