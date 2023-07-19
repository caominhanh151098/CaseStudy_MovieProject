package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.Follow;
import com.example.casestudy_movieproject.model.Movie;
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
            " left join Person p on e.person.id = p.id " +
            "   left join MovieGenre mg on m.id = mg.movie.id " +
            " left join Genre g on mg.genre.id = g.id where " +
                    "m.name like :search or " +
                    "lower(m.type) like :search or " +
                    "p.name like :search or " +
                    "g.name like :search")
    Page<Movie> searchAll(String search, Pageable pageable);


}
