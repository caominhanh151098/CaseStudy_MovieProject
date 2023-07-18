package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Page<Comment> findAllByMovie_Id(int id, Pageable pageable);
    int countAllByMovie_Id(int movie_id);
}
