package com.example.casestudy_movieproject.controller.rest;

import com.example.casestudy_movieproject.model.Genre;
import com.example.casestudy_movieproject.service.genre.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/genre")
public class GenreRestController {
    private final GenreService genreService;

    @GetMapping
    public List<Genre> getAll() {
        return genreService.findAll();
    }
}
