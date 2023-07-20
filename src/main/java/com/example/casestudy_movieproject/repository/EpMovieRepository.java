package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.EpMovie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpMovieRepository extends JpaRepository<EpMovie, Integer> {
    List<EpMovie> findAllByMovie_Id(int id);

    EpMovie findById(int id);
}