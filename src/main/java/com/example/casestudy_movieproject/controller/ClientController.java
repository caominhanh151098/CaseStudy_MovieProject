package com.example.casestudy_movieproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class ClientController {

    @GetMapping
    public ModelAndView goHome() {
        ModelAndView model = new ModelAndView("redirect:/client");
        return model;
    }
    @GetMapping("client")
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("client/index");
        return model;
    }

    @GetMapping("client/tat-ca")
    public ModelAndView showAllMovie() {
        ModelAndView model = new ModelAndView("client/list_movie");
        model.addObject("showList", 1);
        return model;
    }

    @GetMapping("client/the-loai/{id}")
    public ModelAndView showGenreMovie(@PathVariable int id) {
        ModelAndView model = new ModelAndView("client/list_movie");
        model.addObject("showList", 2);
        return model;
    }

    @GetMapping("client/phim-bo")
    public ModelAndView showEpMovie() {
        ModelAndView model = new ModelAndView("client/list_movie");
        model.addObject("showList", 3);
        return model;
    }
    @GetMapping("client/phim/{id}")
    public ModelAndView movieDetail(@PathVariable int id) {
        ModelAndView model = new ModelAndView("client/film_details");
        return model;
    }
    @GetMapping("client/xem-phim/{id}-{idEp}")
    public ModelAndView watchMovie(@PathVariable int id, @PathVariable int idEp) {
        ModelAndView model = new ModelAndView("client/watch-film");
        return model;
    }
}