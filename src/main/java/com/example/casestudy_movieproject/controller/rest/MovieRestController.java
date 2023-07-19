package com.example.casestudy_movieproject.controller.rest;


import com.example.casestudy_movieproject.model.Movie;
import com.example.casestudy_movieproject.service.MovieService;
import com.example.casestudy_movieproject.service.movie.response.MovieListResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/movies")
@AllArgsConstructor
public class MovieRestController {

    private final MovieService movieService;


    @GetMapping
    public List<MovieListResponse> findAll(){
        List<MovieListResponse> movies = movieService.findAll();
        return movieService.findAll();
    }



}
