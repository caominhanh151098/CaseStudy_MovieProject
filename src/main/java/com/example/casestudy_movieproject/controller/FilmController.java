package com.example.casestudy_movieproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/phim/*")
public class FilmController {

    @GetMapping
    public ModelAndView filmDetail() {
        ModelAndView model = new ModelAndView("client/film_details");
        return model;
    }
}
