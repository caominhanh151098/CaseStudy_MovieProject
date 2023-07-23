package com.example.casestudy_movieproject.controller.rest;

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

    @GetMapping
    public int getScoreByUserAndMovie(@RequestParam(defaultValue = "0") String userId,  @RequestParam(defaultValue = "0") String movieId) {
        return voteService.getScoreByUserAndMovie(userId, movieId);
    }

    @GetMapping("/avg/{id}")
    public int getAvgScore(@PathVariable int id) {
        return voteService.getAvgScore(id);
    }

    @GetMapping("/numVote")
    public int getVoteByMovie(@RequestParam(defaultValue = "0") String movieId) {
        return voteService.getVoteByMovie(movieId);
    }
    @PostMapping
    public ResponseEntity<?> saveVote(@RequestBody VoteSaveRequest voteRequest) {
        voteService.saveVote(voteRequest);
        return ResponseEntity.noContent().build();
    }
}
