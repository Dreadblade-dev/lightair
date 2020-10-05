package com.dreadblade.lightair.controller;

import com.dreadblade.lightair.domain.Comment;
import com.dreadblade.lightair.domain.User;
import com.dreadblade.lightair.domain.Views;
import com.dreadblade.lightair.service.CommentService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @JsonView(Views.FullComment.class)
    public Comment create(@RequestBody Comment comment,
                          @AuthenticationPrincipal User user) {

        return commentService.create(comment, user);
    }
}
