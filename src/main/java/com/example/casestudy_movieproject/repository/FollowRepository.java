package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.Follow;
import com.example.casestudy_movieproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {
    Follow findByMovie_IdAndUser(int movie_id, User user);

    List<Follow> findAllByUser(User user);
}
