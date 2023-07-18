package com.example.casestudy_movieproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class HomeController {
    @GetMapping
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("client/index");
        return model;
    }

    @GetMapping("/test")
    public ModelAndView test() {
        ModelAndView model = new ModelAndView("test");
        return model;
    }
}
