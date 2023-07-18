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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name ="user_Id")
    private User user;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_Id")
    private Movie movie;
    private LocalDateTime timeComment;
}
