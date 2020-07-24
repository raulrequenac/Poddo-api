package com.poddo.edgeservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.poddo.edgeservice.clients.CommentClient;
import com.poddo.edgeservice.exceptions.CommentServiceException;
import com.poddo.edgeservice.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentClient commentClient;
    @Autowired
    private UserService userService;

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public List<Comment> findAll() {
        return commentClient.findAll();
    }

    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public Comment findById(Long id) {
        return commentClient.findById(id);
    }

    @HystrixCommand(fallbackMethod = "createFallback")
    public Comment create(Comment comment) {
        return commentClient.create(comment);
    }

    @HystrixCommand(fallbackMethod = "starFallback")
    public Comment star(Long id) {
        return commentClient.star(id);
    }

    @HystrixCommand(fallbackMethod = "updateFallback")
    public Comment update(Long id, Comment comment) {
        return commentClient.update(id, comment);
    }

    @HystrixCommand(fallbackMethod = "removeFallback")
    public void remove(Long id) {
        commentClient.remove(id);
    }

    public List<Comment> findAllFallback() {
        throw new CommentServiceException("findAll");
    }

    public Comment findByIdFallback(Long id) {
        throw new CommentServiceException("findById");
    }

    public Comment createFallback(Comment comment) {
        throw new CommentServiceException("create");
    }

    public Comment starFallback(Long id) {
        throw new CommentServiceException("star");
    }

    public Comment updateFallback(Long id, Comment comment) {
        throw new CommentServiceException("update");
    }

    public void removeFallback(Long id) {
        throw new CommentServiceException("remove");
    }
}
