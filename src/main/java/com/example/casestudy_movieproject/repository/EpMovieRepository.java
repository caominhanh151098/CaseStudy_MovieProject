package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.EpMovie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EpMovieRepository extends JpaRepository<EpMovie, Integer> {
    List<EpMovie> findAllByMovie_Id(int id);

    EpMovie findById(int id);

    int countAllByMovie_Id(int id);

}
