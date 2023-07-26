package com.example.casestudy_movieproject.controller.rest;

import com.example.casestudy_movieproject.service.view.ViewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/views")
@AllArgsConstructor
public class ViewRestController {
    private final ViewService viewService;

    @PostMapping
    public ResponseEntity<?> increaseMovieViews(@RequestBody int idMovie) {
        viewService.increaseMovieViews(idMovie);
        return ResponseEntity.noContent().build();
    }
}
