package com.poddo.commentservice.controller.interfaces;

import com.poddo.commentservice.dto.CommentDto;
import com.poddo.commentservice.model.Comment;

import java.util.List;

public interface ICommentController {
    List<Comment> findAll();
    Comment findById(Long id);
    Comment create(CommentDto comment);
    Comment star(Long id);
    Comment update(Long id, Comment comment);
    void remove(Long id);
}
