package com.example.casestudy_movieproject.service.movie.response;

import com.example.casestudy_movieproject.service.ep_movie.reponse.ShowListEpisodeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowUrlMovieResponse {
    private String id;
    private String name;
    private String genre;
    private List<ShowListEpisodeResponse> seriesMovie;
}
