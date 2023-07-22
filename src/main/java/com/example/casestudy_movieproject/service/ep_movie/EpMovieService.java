package com.example.casestudy_movieproject.service.ep_movie;

import com.example.casestudy_movieproject.model.EpMovie;
import com.example.casestudy_movieproject.repository.EpMovieRepository;
import com.example.casestudy_movieproject.service.movie.response.WatchMovieResponse;
import com.example.casestudy_movieproject.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EpMovieService {
    private final EpMovieRepository epMovieRepository;

    public WatchMovieResponse getMovie(int id){
        return AppUtils.mapper.map(epMovieRepository.findById(id), WatchMovieResponse.class) ;
    }
}
