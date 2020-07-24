package com.poddo.commentservice.service;

import com.poddo.commentservice.exceptions.IdNotFoundException;
import com.poddo.commentservice.exceptions.RespondingAResponseException;
import com.poddo.commentservice.model.Comment;
import com.poddo.commentservice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service of Comment Service
 */
@Service
public class CommentService {
    /**
     * Comment repository
     */
    @Autowired
    private CommentRepository commentRepository;

    /**
     * Find all Comments
     * @return List of Comments
     */
    public List<Comment> findAll() {
        return commentRepository.findByResponseTo(null);
    }

    /**
     * Find Comment by ID
     * @param id ID of the Comment
     * @return Comment with the ID provided
     */
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Comment with id "+id+" not found."));
    }

    /**
     * Create Comment
     * @param comment Comment to be created
     * @return Comment created
     */
    public Comment create(Comment comment) {
        Comment responseTo = comment.getResponseTo();
        if (responseTo!=null && responseTo.getResponseTo()!=null)
            throw new RespondingAResponseException("The comment with id "+responseTo.getId()+" is a response.");

        return commentRepository.save(new Comment(comment.getUserId(), comment.getText(), responseTo));
    }

    /**
     * Star Comment
     * @param id ID of Comment to star
     * @return Comment with ID provided
     */
    public Comment star(Long id) {
        Comment comment = findById(id);
        comment.addStar();

        return commentRepository.save(comment);
    }

    /**
     * Update Comment
     * @param id ID of Comment to update
     * @param commentUpdate Comment updates
     * @return Comment updated with ID provided
     */
    public Comment update(Long id, Comment commentUpdate){
        Comment comment = findById(id);

        if (commentUpdate.getText()!=null) comment.setText(commentUpdate.getText());

        return commentRepository.save(comment);
    }

    /**
     * Remove Comment
     * @param id ID of Comment to remove
     */
    public void remove(Long id) {
        commentRepository.deleteById(id);
    }
}
