package com.example.casestudy_movieproject.controller.rest;

import com.example.casestudy_movieproject.model.User;
import com.example.casestudy_movieproject.service.user.UserService;
import com.example.casestudy_movieproject.service.vote.VoteService;
import com.example.casestudy_movieproject.service.vote.request.VoteSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vote")
@AllArgsConstructor
public class VoteRestController {
    private final VoteService voteService;
    private final UserService userService;
    static User user;

    @GetMapping
    public int getScoreByUserAndMovie(@RequestParam(defaultValue = "0") int movieId) {
        User user = userService.getUser();
        if (user != null)
            return voteService.getScoreByUserAndMovie(user, movieId);
        else return voteService.getAvgScore(movieId);
    }

    @GetMapping("/check")
    private boolean checkUser(){
        user = userService.getUser();
        return user == null ? false : true;
    }

    @GetMapping("/numVote")
    public int getVoteByMovie(@RequestParam(defaultValue = "0") String movieId) {
        return voteService.getVoteByMovie(movieId);
    }

    @PostMapping
    public ResponseEntity<?> saveVote(@RequestBody VoteSaveRequest voteRequest) {
        User user = userService.getUser();
        voteService.saveVote(user, voteRequest);
        return ResponseEntity.noContent().build();
    }
}
