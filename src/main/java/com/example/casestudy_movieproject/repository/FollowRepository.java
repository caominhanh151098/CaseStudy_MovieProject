package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {
}
