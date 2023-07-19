package com.example.casestudy_movieproject.controller;

import com.example.casestudy_movieproject.service.movie.request.MovieSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public ModelAndView adminPage(){
        ModelAndView model = new ModelAndView("admin/index");
        return model;
    }

    @GetMapping("/showMovie")
    public ModelAndView showMovieList(){
        ModelAndView model = new ModelAndView("/admin/showMovie");
//        model.addObject("movies",movieService.findAll());
        return model;
    }

    @GetMapping("/create")
    public ModelAndView showMovieCreate(){
        ModelAndView model = new ModelAndView("/admin/createMovie");
        model.addObject("movie",new MovieSaveRequest());
        return model;
    }
}
