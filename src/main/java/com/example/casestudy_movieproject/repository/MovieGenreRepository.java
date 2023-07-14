package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, Integer> {
}
