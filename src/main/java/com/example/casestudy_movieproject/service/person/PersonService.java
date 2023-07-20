package com.example.casestudy_movieproject.service.person;

import com.example.casestudy_movieproject.model.Person;
import com.example.casestudy_movieproject.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> findAll(){
        return personRepository.findAll();
    }
}
