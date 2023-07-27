package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRepository extends JpaRepository<View, Integer> {
    View findViewByMovie_IdAndSessionId(int idMovie, String sessionId);

    int countAllByMovie_Id(int movie_id);


}
