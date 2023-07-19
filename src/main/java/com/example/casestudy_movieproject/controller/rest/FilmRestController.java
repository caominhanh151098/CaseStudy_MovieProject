package com.example.casestudy_movieproject.controller.rest;

import com.example.casestudy_movieproject.model.Movie;
import com.example.casestudy_movieproject.repository.GenreRepository;
import com.example.casestudy_movieproject.repository.MovieRepository;
import com.example.casestudy_movieproject.service.MovieService;
import com.example.casestudy_movieproject.service.movie.response.MovieListResponse;
import com.example.casestudy_movieproject.service.movie.response.ShowMovieDetail;
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
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

//    @GetMapping
//    public Page<MovieListResponse> showAll(@RequestParam(defaultValue = "") String search,
//                                           Pageable pageable){
//        List<Movie> movieList = movieRepository.findAll();
//        return movieService.findAllWithSearchAndPaging(search,pageable);
//    }

//    @GetMapping
//    public List<Movie> showAll(){
//        List<Movie> movieList = movieService.findAll();
//        return movieService.findAll();
//    }
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
