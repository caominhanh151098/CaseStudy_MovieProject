package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.Follow;
import com.example.casestudy_movieproject.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findById(int id);

    List<Movie> findAll();
}
