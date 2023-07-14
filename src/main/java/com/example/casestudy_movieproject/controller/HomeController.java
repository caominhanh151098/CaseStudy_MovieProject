package com.example.casestudy_movieproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("layout");
        return model;
    }
}
