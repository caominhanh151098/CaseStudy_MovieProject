package com.example.casestudy_movieproject.controller;

import com.example.casestudy_movieproject.model.User;
import com.example.casestudy_movieproject.repository.UserRepository;
import com.example.casestudy_movieproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("phim")
public class UserController {
    private UserRepository userRepository;
    private UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public String showUser(Model model){
        model.addAttribute("phim",userRepository.findAll());
        return"login";
    }

}
