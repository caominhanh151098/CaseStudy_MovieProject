package com.example.casestudy_movieproject.controller.rest;

import com.example.casestudy_movieproject.model.User;
import com.example.casestudy_movieproject.service.comment.CommentService;
import com.example.casestudy_movieproject.service.comment.request.CommentSaveRequest;
import com.example.casestudy_movieproject.service.comment.response.ShowCommentResponse;
import com.example.casestudy_movieproject.service.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comment")
public class CommentRestController {
    private final CommentService commentService;
    private final UserService userService;

    static User user;

    @GetMapping("/{id}")
    public Page<ShowCommentResponse> commentAtMovie(@PathVariable int id, Pageable pageable) {
        return commentService.getCommentByMovieId(id, pageable);
    }

    @PostMapping
    public ResponseEntity<?> saveComment(@Valid @RequestBody CommentSaveRequest comment, BindingResult result) {
        if (result.hasErrors())
            return ResponseEntity.noContent().build();
        user = userService.getUser();
        commentService.saveComment(user, comment);
        return ResponseEntity.noContent().build();
    }
}
