package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.EpMovie;
import com.example.casestudy_movieproject.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EpMovieRepository extends JpaRepository<EpMovie, Integer> {
    List<EpMovie> findAllByMovie_Id(int id);

    EpMovie findById(int id);


    int countAllByMovie_Id(int id);

    @Modifying
    @Query(value = "delete from EpMovie e where e.movie.id = :movie_id")
    void deleteEpMovieByMovie_Id(int movie_id);

    boolean existsById(int id);


}
