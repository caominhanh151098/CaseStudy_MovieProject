package com.example.casestudy_movieproject.controller.rest;

import com.example.casestudy_movieproject.model.Movie;
import com.example.casestudy_movieproject.repository.MovieRepository;
import com.example.casestudy_movieproject.service.MovieService;
import com.example.casestudy_movieproject.service.movie.response.ShowMovieDetail;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/phim")
public class FilmRestController {
    private final MovieService movieService;

    @GetMapping("/{id}")
    public ShowMovieDetail getDetail(@PathVariable int id) {
        return movieService.showDetail(id);
    }

    @GetMapping("/xem-phim/{id}")
    public ShowMovieDetail watchMovie(@PathVariable int id) {
//        return movieService.showDetail(id);
        return null;
    }
}
