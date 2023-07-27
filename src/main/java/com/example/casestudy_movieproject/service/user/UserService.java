package com.example.casestudy_movieproject.service.user;

import com.example.casestudy_movieproject.model.User;
import com.example.casestudy_movieproject.repository.UserRepository;
import com.example.casestudy_movieproject.service.request.UserSaveRequest;
import com.example.casestudy_movieproject.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<User> findByUsernameIgnoreCase(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }
    public List<User> findAll(){
      return   userRepository.findAll();

    }


}