package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.Movie;
import com.example.casestudy_movieproject.service.movie.response.MovieListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findById(int id);


    @Query(value = "SELECT m FROM Movie m left join EKip e on m.id = e.movie.id " +
            " join Person p on e.person.id = p.id " +
            "   join MovieGenre mg on m.id = mg.movie.id " +
            " join Genre g on mg.genre.id = g.id where " +
                    " m.name like :search or " +
                    " lower(m.type) like :search or " +
                    " p.name like :search or " +
                    " g.name like :search")
    Page<Movie> searchAll(String search, Pageable pageable);

//    @Query(value = "SELECT new MovieListResponse(m.name,m.movieGenres) FROM Movie m left join EKip e on m.id = e.movie.id " +
//            " join Person p on e.person.id = p.id " +
//            "   join MovieGenre mg on m.id = mg.movie.id " +
//            " join Genre g on mg.genre.id = g.id where " +
//            " m.name like :search or " +
//            " lower(m.type) like :search or " +
//            " p.name like :search or " +
//            " g.name like :search")
//    Page<MovieListResponse> searchAll1(String search, Pageable pageable);

    @Query(value = "SELECT m FROM Movie m " +
            "JOIN MovieGenre mg ON m.id = mg.movie.id " +
            "WHERE m.status != 'COMING_SOON' AND m.status != 'CANCEL' " +
            "AND mg.genre.id = :idGenre")
    Page<Movie> getMovieByGenre(String idGenre, Pageable pageable);

    @Query(value = "SELECT m FROM Movie m " +
            "JOIN MovieGenre mg ON m.id = mg.movie.id " +
            "WHERE m.status != 'COMING_SOON' AND m.status != 'CANCEL' " +
            "AND m.type = 'SERIES'")
    Page<Movie> getMovieSeries(Pageable pageable);


}
