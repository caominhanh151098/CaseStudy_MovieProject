package com.example.casestudy_movieproject.model;

import com.example.casestudy_movieproject.model.enums.EQuality;
import com.example.casestudy_movieproject.model.enums.EStatus;
import com.example.casestudy_movieproject.model.enums.EType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Where(clause = "status != 0")
@SQLDelete(sql = "UPDATE movie  SET status = 1 WHERE (`id` = ?);")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_series")
    private Movie series;
    @OneToMany(mappedBy = "series")
    private Set<Movie> series_movie;
    @Column(nullable = false)
    private String name;
    private int airedYear;
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
    @Column(name = "poster",nullable = false)
    private String img_poster;
    private String img_movie;

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

    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EType type;
}