package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.Movie;
import com.example.casestudy_movieproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
