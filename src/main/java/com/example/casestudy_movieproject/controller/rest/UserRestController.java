package com.example.casestudy_movieproject.controller.rest;

import com.example.casestudy_movieproject.model.User;
import com.example.casestudy_movieproject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/user")
public class UserRestController {
    @Autowired
    private UserService userService;
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);

    }



}
