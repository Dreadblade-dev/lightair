package com.dreadblade.lightair.service;

import com.dreadblade.lightair.domain.Comment;
import com.dreadblade.lightair.domain.User;
import com.dreadblade.lightair.domain.Views;
import com.dreadblade.lightair.dto.EventType;
import com.dreadblade.lightair.dto.ObjectType;
import com.dreadblade.lightair.repo.CommentRepo;
import com.dreadblade.lightair.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepo commentRepo, WsSender wsSender) {
        this.commentRepo = commentRepo;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        Comment commentFromDb = commentRepo.save(comment);

        wsSender.accept(EventType.CREATE, commentFromDb);

        return commentFromDb;
    }
}
