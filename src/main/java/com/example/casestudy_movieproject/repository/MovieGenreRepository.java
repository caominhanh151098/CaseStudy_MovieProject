package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, Integer> {
    List<MovieGenre> findAllByMovie_Id(int id);

    MovieGenre findTopByMovie_Id(int id);

    MovieGenre findByIdAndMovie_Id(int id, int movie_id);

    MovieGenre findByGenre_IdAndMovie_Id(int genre_id, int movie_id);

    boolean existsByIdAndMovie_Id(int id, int movie_id);
    boolean existsByGenre_IdAndMovie_Id(int genre_id, int movie_id);

    @Modifying
    @Query(value = "delete from MovieGenre mg where mg.movie.id = :movie_id")
    void deleteMovieGenreByMovie_Id(int movie_id);
}
