package com.poddo.commentservice.controller.interfaces;

import com.poddo.commentservice.model.Comment;

import java.util.List;

/**
 * Controller Interface of Comment Service
 */
public interface ICommentController {
    /**
     * Find all Comments
     * @return List of Comments
     */
    List<Comment> findAll();

    /**
     * Find Comment by ID
     * @param id ID of Comment
     * @return Comment with ID provided
     */
    Comment findById(Long id);

    /**
     * Create Comment
     * @param comment Comment to be Created
     * @return Comment created
     */
    Comment create(Comment comment);

    /**
     * Star Comment
     * @param id ID of Comment to star
     * @return Comment with ID provided
     */
    Comment star(Long id);

    /**
     * Update Comment
     * @param id ID of Comment to update
     * @param comment Comment to update
     * @return Comment updated with ID provided
     */
    Comment update(Long id, Comment comment);

    /**
     * Remove Comment
     * @param id ID of Comment to remove
     */
    void remove(Long id);
}
