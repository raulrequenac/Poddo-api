package com.poddo.edgeservice.controller.interfaces;

import com.poddo.edgeservice.model.Comment;

import java.util.List;

public interface ICommentController {
    List<Comment> findAll();
    Comment findById(Long id);
    Comment star(Long id);
    Comment update(Long id, Comment comment);
}
