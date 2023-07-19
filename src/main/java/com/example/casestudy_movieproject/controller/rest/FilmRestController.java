package com.example.casestudy_movieproject.controller.rest;


import com.example.casestudy_movieproject.model.Movie;
import com.example.casestudy_movieproject.repository.MovieRepository;
import com.example.casestudy_movieproject.service.movie.MovieService;
import com.example.casestudy_movieproject.service.comment.CommentService;
import com.example.casestudy_movieproject.service.comment.response.ShowCommentResponse;
import com.example.casestudy_movieproject.service.movie.response.ShowMovieDetail;
import com.example.casestudy_movieproject.service.movie.response.ShowUrlMovieResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/phim")
public class FilmRestController {
    private final MovieService movieService;
    private final CommentService commentService;
    private final MovieRepository movieRepository;
    @GetMapping
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public ShowMovieDetail getDetail(@PathVariable int id) {
        return movieService.showDetail(id);
    }

    @GetMapping("/xem-phim/{id}")
    public ShowUrlMovieResponse watchMovie(@PathVariable int id) {
        return movieService.watchMovie(id);
    }

    @GetMapping("/comment/{id}")
    public Page<ShowCommentResponse> commentAtMovie(@PathVariable int id, Pageable pageable) {
        return commentService.getCommentByMovieId(id, pageable);
    }




}
