package com.example.casestudy_movieproject.controller.rest;

import com.example.casestudy_movieproject.model.Follow;
import com.example.casestudy_movieproject.model.User;
import com.example.casestudy_movieproject.service.follow.FollowService;
import com.example.casestudy_movieproject.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/follow")
@AllArgsConstructor
public class FollowRestController {
    private final FollowService followService;
    private final UserService userService;
    static User user;


    @GetMapping("/{id}")
    public boolean checkLikeMovie(@PathVariable int id) {
        user = userService.getUser();
        if (user != null) {
            Follow follow = followService.checkLikeMovie(user, id);
            return follow != null ? true : false;
        } else
            return false;
    }

    @GetMapping("/like/{id}")
    public void likeOrDisLikeMovie(@PathVariable int id) {
        user = userService.getUser();
        if (user != null) {
            followService.likeOrDisLikeMovie(user, id);
        }
    }
}
