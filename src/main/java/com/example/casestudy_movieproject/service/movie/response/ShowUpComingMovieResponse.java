package com.example.casestudy_movieproject.service.movie.response;

import com.example.casestudy_movieproject.service.genre.response.ShowGenreByMovieResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowUpComingMovieResponse {
    private String id;
    private String name;
    private String img_movie;
    private List<ShowGenreByMovieResponse> genres;
    private String commentNum;
}
