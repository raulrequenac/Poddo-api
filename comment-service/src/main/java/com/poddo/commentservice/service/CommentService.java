package com.poddo.commentservice.service;

import com.poddo.commentservice.dto.CommentDto;
import com.poddo.commentservice.exceptions.IdNotFoundException;
import com.poddo.commentservice.exceptions.RespondingAResponseException;
import com.poddo.commentservice.model.Comment;
import com.poddo.commentservice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAll() {
        return commentRepository.findByResponseTo(null);
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Comment with id "+id+" not found."));
    }

    public Comment create(CommentDto comment) {
        Long responseToId = comment.getResponseTo();
        Comment responseTo = responseToId == null ? null : findById(responseToId);
        if (responseTo!=null && responseTo.getResponseTo()!=null)
            throw new RespondingAResponseException("The comment with id "+responseToId+" is a response.");

        return commentRepository.save(new Comment(comment.getUserId(), comment.getText(), responseTo));
    }

    public Comment star(Long id) {
        Comment comment = findById(id);
        comment.addStar();

        return commentRepository.save(comment);
    }

    public Comment update(Long id, Comment commentUpdate){
        Comment comment = findById(id);

        if (commentUpdate.getText()!=null) comment.setText(commentUpdate.getText());

        return commentRepository.save(comment);
    }

    public void remove(Long id) {
        commentRepository.deleteById(id);
    }
}
