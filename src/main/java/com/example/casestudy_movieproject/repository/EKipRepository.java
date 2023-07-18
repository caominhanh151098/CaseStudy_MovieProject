package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.EKip;
import com.example.casestudy_movieproject.model.enums.ERoleEKip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EKipRepository extends JpaRepository<EKip, Integer> {
    List<EKip> findAllByMovie_Id(int movie_id);

}
