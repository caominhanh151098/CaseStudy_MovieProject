package com.example.casestudy_movieproject.service.ekip;

import com.example.casestudy_movieproject.model.EKip;
import com.example.casestudy_movieproject.repository.EKipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EkipService {
    private final EKipRepository eKipRepository;


    public List<EKip> findAll(){
        return eKipRepository.findAll();
    }
}
