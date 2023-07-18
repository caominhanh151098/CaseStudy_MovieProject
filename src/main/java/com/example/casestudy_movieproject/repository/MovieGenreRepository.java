package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, Integer> {
    List<MovieGenre> findAllByMovie_Id(int id);
}
