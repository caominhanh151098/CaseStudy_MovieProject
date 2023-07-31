package com.example.casestudy_movieproject.service.movie.response;

import com.example.casestudy_movieproject.service.ep_movie.reponse.ShowListEpisodeResponse;
import com.example.casestudy_movieproject.service.genre.response.ShowGenreByMovieResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private ShowGenreByMovieResponse topGenre;
    private String movieGenres;
    private String directors;
    private String actors;
    private String commentNum;
    private String viewNum;
    private String status;
    private String type;
    private String totalEp;
    private List<ShowListEpisodeResponse> seriesMovie;
    private String urlTrailer;
}
