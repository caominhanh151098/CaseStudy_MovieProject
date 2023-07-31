package com.example.casestudy_movieproject.controller.rest;

import com.example.casestudy_movieproject.model.User;
import com.example.casestudy_movieproject.service.user.UserService;
import com.example.casestudy_movieproject.service.user.response.UserListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public ResponseEntity<?> getAll(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/client")
    public boolean checkUser() {
        User user = userService.getUser();
        if (user == null)
            return false;
        return true;
    }



    @GetMapping
    public Page<UserListResponse> findAll( Pageable pageable){
        return userService.finAllpaging( pageable);
    }


}
