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
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int score;
    @ManyToOne
    @JoinColumn(name ="user_Id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "movie_Id")
    private Movie movie;
}
