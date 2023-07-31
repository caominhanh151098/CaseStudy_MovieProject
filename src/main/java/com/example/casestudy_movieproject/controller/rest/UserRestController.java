package com.example.casestudy_movieproject.controller.rest;

import com.example.casestudy_movieproject.model.User;
import com.example.casestudy_movieproject.service.user.UserService;
import com.example.casestudy_movieproject.service.user.request.SaveAvatarRequest;
import com.example.casestudy_movieproject.service.user.request.SaveUserInfoRequest;
import com.example.casestudy_movieproject.service.user.response.ShowUserInfoResponse;
import com.example.casestudy_movieproject.service.user.response.UserListResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public ResponseEntity<?> getAll() {
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

    @GetMapping("/client/info")
    public ShowUserInfoResponse showInfo() {
        return userService.showInfoUserClient();
    }

    @PutMapping("/client/info")
    public void editInfo(@Valid @RequestBody SaveUserInfoRequest request, BindingResult result){
        if (result.hasErrors()) {
            return;
        }
        userService.editInfo(request);
    }
    @PutMapping("/client/info/avatar")
    public void changeAvatar(@RequestBody SaveAvatarRequest request) {
        userService.changeAvatar(request);
    }


    @GetMapping
    public Page<UserListResponse> findAll(Pageable pageable) {

        return userService.finAllpaging(pageable);
    }

}
