package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.Movie;
import com.example.casestudy_movieproject.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    Vote findByUser_IdAndMovie_Id(int user_id, int movie_id);

    int countByMovie_id(int movie_id);

    List<Vote> findAllByMovie_Id(int movie_id);
}
