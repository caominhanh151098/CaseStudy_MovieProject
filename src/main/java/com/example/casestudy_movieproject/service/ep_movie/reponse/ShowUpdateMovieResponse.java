package com.example.casestudy_movieproject.service.ep_movie.reponse;

import com.example.casestudy_movieproject.service.movie.response.ShowListMovieResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowUpdateMovieResponse {
    private ShowListMovieResponse movie;
}
