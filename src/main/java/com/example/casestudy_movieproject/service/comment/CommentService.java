package com.example.casestudy_movieproject.service.comment;

import com.example.casestudy_movieproject.model.Comment;
import com.example.casestudy_movieproject.repository.CommentRepository;
import com.example.casestudy_movieproject.service.comment.response.ShowCommentResponse;
import com.example.casestudy_movieproject.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Page<ShowCommentResponse> getCommentByMovieId(int id, Pageable pageable) {
        Page<Comment> commentList =  commentRepository.findAllByMovie_Id(id, pageable);
        for(Comment comment : commentList)
            comment.setTimeComment(comment.getTimeComment().minusHours(7));
        Page<ShowCommentResponse> commentResponses = commentList.map(e -> AppUtils.mapper.map(e, ShowCommentResponse.class));
        return commentResponses;
    }
}
