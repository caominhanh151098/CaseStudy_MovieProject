package com.example.casestudy_movieproject.service.ep_movie;

import com.example.casestudy_movieproject.model.EpMovie;
import com.example.casestudy_movieproject.repository.EpMovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EpMovieService {
    private final EpMovieRepository epMovieRepository;

    public EpMovie getMovie(int id){
        return epMovieRepository.findById(id);
    }
}
