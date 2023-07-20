package com.example.casestudy_movieproject.repository;

import com.example.casestudy_movieproject.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

}
