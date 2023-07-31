package com.example.casestudy_movieproject.controller;

import com.example.casestudy_movieproject.repository.UserRepository;
import com.example.casestudy_movieproject.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
@AllArgsConstructor
public class UserController {
    private UserRepository userRepository;
    private UserService userService;



}

