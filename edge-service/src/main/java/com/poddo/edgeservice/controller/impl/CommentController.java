package com.poddo.edgeservice.controller.impl;

import com.poddo.edgeservice.controller.interfaces.ICommentController;
import com.poddo.edgeservice.model.Comment;
import com.poddo.edgeservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController implements ICommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> findAll() {
        return commentService.findAll();
    }

    @GetMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment findById(@PathVariable Long id) {
        return commentService.findById(id);
    }

    @PostMapping("/comments/{id}/star")
    @ResponseStatus(HttpStatus.OK)
    public Comment star(@PathVariable Long id) {
        return commentService.star(id);
    }

    @PatchMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment update(@PathVariable Long id, @RequestBody Comment comment) {
        return commentService.update(id, comment);
    }
}
