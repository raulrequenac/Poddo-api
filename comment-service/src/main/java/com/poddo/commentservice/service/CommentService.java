package com.poddo.commentservice.service;

import com.poddo.commentservice.exceptions.IdNotFoundException;
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
        return commentRepository.findAll();
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Comment with id "+id+" not found."));
    }

    public Comment create(Comment comment) {
        return commentRepository.save(comment);
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
