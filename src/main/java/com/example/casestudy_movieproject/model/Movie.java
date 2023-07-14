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
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate airedDate;
    private double scoreIMDb;
    private int duration;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EQuality quality;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;
    @Column(columnDefinition = "LONGTEXT")
    private String url;
    @Column(columnDefinition = "LONGTEXT")
    private String urlTrailer;
    @Column(nullable = false)
    private String img;
    private String img2;

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

    @OneToMany(mappedBy = "movie")
    private Set<EKip> eKips;
}
