package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.EKip;
import com.example.casestudy_movieproject.model.enums.ERoleEKip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EKipRepository extends JpaRepository<EKip, Integer> {
    List<EKip> findAllByMovie_Id(int movie_id);

    @Modifying
    @Query(value = "delete from EKip e where e.movie.id = :movie_id")
    void deleteEKipByMovie_Id(int movie_id);

}
