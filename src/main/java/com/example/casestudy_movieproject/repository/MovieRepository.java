package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findById(int id);

    @Query(value = "select m from Movie  m where lower(m.name) like lower(:search) or " +
                                                "lower(m.type) like lower(:search) ")
    Page<Movie> searchAllWithPage(String search , Pageable pageable);

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
            "WHERE m.status != 'COMING_SOON' AND m.status != 'CANCEL'")
    Page<Movie> getMovieUpdate(Pageable pageable);

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

    @Query(value = "SELECT m FROM Movie m " +
            "WHERE m.status = 'COMING_SOON'")
    Page<Movie> getMovieComingSoon(Pageable pageable);

    @Query(value = "SELECT m FROM Movie m " +
            "WHERE m.status != 'COMING_SOON' AND m.status != 'CANCEL' " +
            "AND m.id != :idMovie " +
            "ORDER BY RAND() limit 4")
    List<Movie> getRandomWithoutMovie_Id(int idMovie);


    @Query(value = "Select m From Movie  m  LEFT Join View v on m.id = v.movie.id group by m.id order by count(m.id) desc")
    Page<Movie> findMovieByViewDESC(Pageable pageable);

    @Query(value = "SELECT m.* FROM movies m JOIN view v ON m.id = v.movie_id WHERE v.time BETWEEN SUBDATE(LOCALTIME(), INTERVAL 1 DAY) AND LOCALTIME() GROUP BY m.id ORDER BY COUNT(v.id) DESC LIMIT 5", nativeQuery = true)
    List<Movie> getMovieTopViewOfDay();
    @Query(value = "SELECT m.* FROM movies m JOIN view v ON m.id = v.movie_id WHERE v.time BETWEEN SUBDATE(LOCALTIME(), INTERVAL 1 WEEK) AND LOCALTIME() GROUP BY m.id ORDER BY COUNT(v.id) DESC LIMIT 5", nativeQuery = true)
    List<Movie> getMovieTopViewOfWeek();
    @Query(value = "SELECT m.* FROM movies m JOIN view v ON m.id = v.movie_id WHERE v.time BETWEEN SUBDATE(LOCALTIME(), INTERVAL 1 MONTH) AND LOCALTIME() GROUP BY m.id ORDER BY COUNT(v.id) DESC LIMIT 5", nativeQuery = true)
    List<Movie> getMovieTopViewOfMonth();
    @Query(value = "SELECT m.* FROM movies m JOIN view v ON m.id = v.movie_id WHERE v.time BETWEEN SUBDATE(LOCALTIME(), INTERVAL 1 YEAR) AND LOCALTIME() GROUP BY m.id ORDER BY COUNT(v.id) DESC LIMIT 5", nativeQuery = true)
    List<Movie> getMovieTopViewOfYear();

}
