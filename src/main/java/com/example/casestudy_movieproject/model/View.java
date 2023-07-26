package com.example.casestudy_movieproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class View {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    private String sessionId;
    private LocalDateTime time;

    public View(Movie movie, String sessionId, LocalDateTime time) {
        this.movie = movie;
        this.sessionId = sessionId;
        this.time = time;
    }
}
