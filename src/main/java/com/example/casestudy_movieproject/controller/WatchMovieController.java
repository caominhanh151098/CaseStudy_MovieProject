package com.example.casestudy_movieproject.controller;

import com.example.casestudy_movieproject.service.movie.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/xem-phim")
@Controller
@AllArgsConstructor
public class WatchMovieController {
    private final MovieService movieService;

    @GetMapping("/*")
    public ModelAndView watchMovie() {
        ModelAndView model = new ModelAndView("client/watch-film");
        return model;
    }
}
