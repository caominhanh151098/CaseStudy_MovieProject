package com.example.casestudy_movieproject.controller;

import com.example.casestudy_movieproject.model.Movie;
import com.example.casestudy_movieproject.model.Person;
import com.example.casestudy_movieproject.model.enums.EQuality;
import com.example.casestudy_movieproject.model.enums.ERoleEKip;
import com.example.casestudy_movieproject.model.enums.EStatus;
import com.example.casestudy_movieproject.model.enums.EType;
import com.example.casestudy_movieproject.service.ekip.EkipService;
import com.example.casestudy_movieproject.service.genre.GenreService;
import com.example.casestudy_movieproject.service.movie.MovieService;
import com.example.casestudy_movieproject.service.movie.request.MovieSaveRequest;
import com.example.casestudy_movieproject.service.person.PersonService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final PersonService personService;
    private final GenreService genreService;
    private final MovieService movieService;
    private final EkipService ekipService;

    @Autowired
    ServletContext context;

    @GetMapping
    public ModelAndView adminPage(){
        ModelAndView model = new ModelAndView("admin/index");
        return model;
    }

    @GetMapping("/showMovie")
    public ModelAndView showMovieList(){

        String absolutePath = context.getRealPath("/");
        ModelAndView model = new ModelAndView("/admin/showMovie");
//        model.addObject("movies",movieService.findAll());
        return model;
    }

    @GetMapping("/create")
    public ModelAndView showMovieCreate(){
        ModelAndView model = new ModelAndView("/admin/createMovie");
        model.addObject("movie",new MovieSaveRequest());
//        model.addObject("seriesMovie",new ArrayList<>());
        List<Person> s = personService.findAll();
        model.addObject("persons",personService.findAll());
        model.addObject("qualities", EQuality.values());
        model.addObject("types", EType.values());
        model.addObject("statuses", EStatus.values());
        model.addObject("genres",genreService.findAll());
        model.addObject("roles", ERoleEKip.values());
        model.addObject("eKips",ekipService.findAll());
        return model;
    }

    @PostMapping("/create")
    public ModelAndView createMovie(@ModelAttribute("movie") MovieSaveRequest movie){
        ModelAndView model = new ModelAndView("redirect:/admin/showMovie");
        MovieSaveRequest movie1 = movie;

        movieService.create(movie);

        return model;
    }

    @GetMapping("{id}")
    public ModelAndView showEditPage(@PathVariable int id){
        ModelAndView model = new ModelAndView("/admin/editMovie");


        model.addObject("movie",movieService.showEdit(id));
        model.addObject("persons",personService.findAll());
        model.addObject("qualities", EQuality.values());
        model.addObject("types", EType.values());
        model.addObject("statuses", EStatus.values());
        model.addObject("genres",genreService.findAll());
        model.addObject("roles", ERoleEKip.values());
        model.addObject("eKips",ekipService.findAll());

        MovieSaveRequest movieSaveRequest = movieService.showEdit(id);



        return model;
    }

    @PostMapping("/edit")
    public ModelAndView editMovie(@ModelAttribute("movie") MovieSaveRequest movie){

        ModelAndView model = new ModelAndView("redirect:/admin/showMovie");
        movieService.edit(movie);
        return model;
    }
}
