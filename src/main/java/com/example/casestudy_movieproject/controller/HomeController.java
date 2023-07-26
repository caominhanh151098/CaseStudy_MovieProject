package com.example.casestudy_movieproject.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/client")
public class HomeController {
    @GetMapping
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("client/index");
        return model;
    }

    @GetMapping("/tat-ca")
    public ModelAndView showAllMovie() {
        ModelAndView model = new ModelAndView("client/list_movie");
        model.addObject("showList", 1);
        return model;
    }

    @GetMapping("/the-loai/{id}")
    public ModelAndView showGenreMovie(@PathVariable int id) {
        ModelAndView model = new ModelAndView("client/list_movie");
        model.addObject("showList", 2);
        return model;
    }

    @GetMapping("/test")
    public ModelAndView test() {
        ModelAndView model = new ModelAndView("test");
        return model;
    }
}
