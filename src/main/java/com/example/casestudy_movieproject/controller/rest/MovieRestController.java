package com.example.casestudy_movieproject.controller.rest;


import com.example.casestudy_movieproject.model.Movie;
import com.example.casestudy_movieproject.repository.MovieRepository;
import com.example.casestudy_movieproject.service.movie.MovieService;
import com.example.casestudy_movieproject.service.movie.response.MovieListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/movies")
@AllArgsConstructor
public class MovieRestController {

    private final MovieService movieService;

    @GetMapping
    public Page<MovieListResponse> findAll(@RequestParam(defaultValue = "") String search, Pageable pageable){
        Page<MovieListResponse> movies = movieService.findAllWithSearchAndPaging(search,pageable);
        return movieService.findAllWithSearchAndPaging(search,pageable);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
