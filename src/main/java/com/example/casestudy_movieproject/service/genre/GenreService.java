package com.example.casestudy_movieproject.service.genre;


import com.example.casestudy_movieproject.model.Genre;
import com.example.casestudy_movieproject.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public List<Genre> findAll(){
        return genreRepository.findAll();
    }
}
