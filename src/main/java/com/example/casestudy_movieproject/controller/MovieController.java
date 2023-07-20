package com.example.casestudy_movieproject.controller;

import com.example.casestudy_movieproject.service.movie.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

//    @GetMapping("/admin/showMovie")
//    public ModelAndView showMovieList(){
//        ModelAndView model = new ModelAndView("/admin/showMovie");
////        model.addObject("movies",movieService.findAll());
//        return model;
//    }
}
