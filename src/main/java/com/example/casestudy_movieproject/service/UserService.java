package com.example.casestudy_movieproject.service;

import com.example.casestudy_movieproject.model.User;
import com.example.casestudy_movieproject.repository.UserRepository;
import com.example.casestudy_movieproject.service.request.UserSaveRequest;
import com.example.casestudy_movieproject.util.AppUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, UserRepository userRepository1) {

        this.userRepository = userRepository;
    }

    public void UserCreate(UserSaveRequest request) {
        User newUser = AppUtils.mapper.map(request, User.class);
        userRepository.save(newUser);
    }
}