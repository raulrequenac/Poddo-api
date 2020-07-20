package com.poddo.edgeservice.service;

import com.poddo.edgeservice.clients.CommentClient;
import com.poddo.edgeservice.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentClient commentClient;

    public List<Comment> findAll() {
        return commentClient.findAll();
    }

    public Comment findById(Long id) {
        return commentClient.findById(id);
    }

    public Comment create(Comment comment) {
        return commentClient.create(comment);
    }

    public Comment star(Long id) {
        return commentClient.star(id);
    }

    public Comment update(Long id, Comment comment) {
        return commentClient.update(id, comment);
    }

    public void remove(Long id) {
        commentClient.remove(id);
    }
}
