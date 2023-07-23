package com.example.casestudy_movieproject.controller.rest;


import com.example.casestudy_movieproject.service.movie.MovieService;
import com.example.casestudy_movieproject.service.movie.response.ShowListMovieResponse;
import com.example.casestudy_movieproject.service.movie.response.ShowMovieDetailResponse;
import com.example.casestudy_movieproject.service.movie.response.ShowMovieResponse;
import com.example.casestudy_movieproject.service.ep_movie.EpMovieService;
import com.example.casestudy_movieproject.service.movie.response.WatchMovieResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/movie")
public class FilmRestController {
    private final MovieService movieService;
    private final EpMovieService epMovieService;

//    @GetMapping
//    public List<Movie> getAll() {
//        return movieRepository.findAll();
//    }

    @GetMapping("/{id}")
    public ShowMovieDetailResponse getDetail(@PathVariable int id) {
        return movieService.showDetail(id);
    }

    @GetMapping("/watch/{id}")
    public ShowMovieResponse watchMovie(@PathVariable int id) {
        return movieService.showMovie(id);
    }

    @GetMapping("/watch_url/{id}")
    public WatchMovieResponse urlMovie(@PathVariable int id) {
        return epMovieService.getMovie(id);
    }

    @GetMapping("/show/all")
    public Page<ShowListMovieResponse> showListMovie(@PageableDefault(size = 12) Pageable pageable) {
        return movieService.showListMovie(pageable);
//        return epMovieService.showListMovieUpdate(pageable);
    }

    @GetMapping("/show/{idGenre}")
    public Page<ShowListMovieResponse> showMoviesByGenre(@PathVariable String idGenre, @PageableDefault(size = 6) Pageable pageable) {
        return movieService.showListMovieByGenre(idGenre, pageable);
    }

    @GetMapping("/show/seriesM")
    public Page<ShowListMovieResponse> showListEPMovie(@PageableDefault(size = 6) Pageable pageable) {
        return movieService.showListEpMovie(pageable);
    }

}
