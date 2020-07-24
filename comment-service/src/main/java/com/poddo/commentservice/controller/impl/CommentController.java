package com.poddo.commentservice.controller.impl;

import com.poddo.commentservice.controller.interfaces.ICommentController;
import com.poddo.commentservice.model.Comment;
import com.poddo.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller of Comment Service
 */
@RestController
public class CommentController implements ICommentController {
    /**
     * CommentService
     */
    @Autowired
    private CommentService commentService;

    /**
     * Find all Comments
     * @return List of Comments
     */
    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> findAll() {
        return commentService.findAll();
    }

    /**
     * Find Comment by ID
     * @param id ID of Comment to be found
     * @return Comment with ID provided
     */
    @GetMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment findById(@PathVariable Long id) {
        return commentService.findById(id);
    }

    /**
     * Create Comment
     * @param comment Comment to be created
     * @return Comment created
     */
    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment comment) {
        return commentService.create(comment);
    }

    /**
     * Star Comment
     * @param id ID of Comment to star
     * @return Comment with ID provided
     */
    @PostMapping("/comments/{id}/star")
    @ResponseStatus(HttpStatus.OK)
    public Comment star(@PathVariable Long id) {
        return commentService.star(id);
    }

    /**
     * Update Comment
     * @param id ID of Comment to be updated
     * @param comment Comment updates
     * @return Comment updated with ID provided
     */
    @PatchMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment update(@PathVariable Long id, @RequestBody Comment comment) {
        return commentService.update(id, comment);
    }

    /**
     * Remove Comment
     * @param id ID of Comment to remove
     */
    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        commentService.remove(id);
    }
}
