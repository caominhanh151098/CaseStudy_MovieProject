package com.example.casestudy_movieproject.controller;

import com.example.casestudy_movieproject.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/phim")
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/*")
    public ModelAndView movieDetail() {
        ModelAndView model = new ModelAndView("client/film_details");
        return model;
    }

    @GetMapping("/xem-phim/*")
    public ModelAndView watchMovie() {
        ModelAndView model = new ModelAndView("client/watch-film");
        return model;
    }
}
