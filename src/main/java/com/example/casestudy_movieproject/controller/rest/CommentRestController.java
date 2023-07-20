package com.example.casestudy_movieproject.controller.rest;

import com.example.casestudy_movieproject.service.comment.CommentService;
import com.example.casestudy_movieproject.service.comment.response.ShowCommentResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comment")
public class CommentRestController {
    private final CommentService commentService;
    @GetMapping("/{id}")
    public Page<ShowCommentResponse> commentAtMovie(@PathVariable int id, Pageable pageable) {
        return commentService.getCommentByMovieId(id, pageable);
    }
}
