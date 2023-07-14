package com.example.casestudy_movieproject.model;

import com.example.casestudy_movieproject.model.enums.EQuality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String studio;
    private LocalDate airedDate;
    private double scoreIMDb;
    private int duration;
    private EQuality quality;
    private String description;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<MovieGenre> movieGenres;

    @OneToMany(mappedBy = "movie")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "movie")
    private Set<Vote> votes;

    @OneToMany(mappedBy = "movie")
    private Set<Follow> follows;

    @OneToMany(mappedBy = "movie")
    private Set<View> views;
}
