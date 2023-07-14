package com.example.casestudy_movieproject.controller.rest;

import com.example.casestudy_movieproject.model.Movie;
import com.example.casestudy_movieproject.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/phim")
public class FilmRestController {
    private final MovieRepository movieRepository;

    @GetMapping("/{id}")
    public Movie getDetail(@PathVariable int id) {
        return movieRepository.findById(id);
    }
}
