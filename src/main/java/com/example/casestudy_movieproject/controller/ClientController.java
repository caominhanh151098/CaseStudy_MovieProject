package com.example.casestudy_movieproject.controller;

import com.example.casestudy_movieproject.service.movie.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class ClientController {
    private final MovieService movieService;

    @GetMapping
    public ModelAndView goHome() {
        ModelAndView model = new ModelAndView("redirect:/client");
        return model;
    }
    @GetMapping("client")
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("client/index");
        model.addObject("randomMovie", movieService.showRandomWithoutMovie(0));
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
    @GetMapping("client/tim-kiem")
    public ModelAndView searchMovie(@RequestParam(defaultValue = "") String search) {
        ModelAndView model = new ModelAndView("client/list_movie");
        model.addObject("showList", 4);
        model.addObject("search", search);
        return model;
    }
    @GetMapping("client/phim/{id}")
    public ModelAndView movieDetail(@PathVariable int id) {
        ModelAndView model = new ModelAndView("client/film_details");
        return model;
    }
    @GetMapping("client/xem-phim/{id}-{idEp}")
    public ModelAndView watchMovie(@PathVariable int id, @PathVariable int idEp) {
        ModelAndView model = new ModelAndView("client/watch_film");
        return model;
    }

    @GetMapping("client/thong-tin-ca-nhan")
    public ModelAndView infoUser() {
        ModelAndView model = new ModelAndView("client/info_user");
        return model;
    }
}
