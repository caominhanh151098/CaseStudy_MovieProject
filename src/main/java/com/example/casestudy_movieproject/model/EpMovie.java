package com.example.casestudy_movieproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ep_movies")
public class EpMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String url;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public EpMovie(String name, String url, Movie movie) {
        this.name = name;
        this.url = url;
        this.movie = movie;
    }
}
