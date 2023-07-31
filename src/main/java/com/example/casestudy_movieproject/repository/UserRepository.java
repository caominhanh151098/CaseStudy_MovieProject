package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.User;
import com.example.casestudy_movieproject.service.user.response.UserListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User,Integer> {
    Optional<User> findByUsernameIgnoreCase(String username);
    boolean existsByUsernameIgnoreCase(String username);
    boolean existsByEmailIgnoreCase(String email);


    User findById(int id);

    User findByUsername(String username);
    List<User> findAll();

}

