package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.Follow;
import com.example.casestudy_movieproject.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    @Query(value = "SELECT g FROM Genre g " +
            "JOIN MovieGenre mg ON g.id = mg.genre.id " +
            "WHERE mg.movie.id = :movie_id")
    List<Genre> getGenreByMovie(int movie_id);
}
