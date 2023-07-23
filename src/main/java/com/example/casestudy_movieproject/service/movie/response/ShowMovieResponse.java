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
public class ShowMovieResponse {
    private String id;
    private String name;
    private ShowGenreByMovieResponse genre;
    private List<ShowListEpisodeResponse> seriesMovie;
}
