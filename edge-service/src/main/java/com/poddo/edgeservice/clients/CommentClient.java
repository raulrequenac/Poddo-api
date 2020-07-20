package com.poddo.edgeservice.clients;

import com.poddo.edgeservice.model.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "comment-service")//, url = "https://channel-service-poddo.herokuapp.com")
public interface CommentClient {
    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    List<Comment> findAll();

    @GetMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    Comment findById(@PathVariable Long id);

    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    Comment create(@RequestBody Comment comment);

    @PostMapping("/comments/{id}/star")
    @ResponseStatus(HttpStatus.OK)
    Comment star(@PathVariable Long id);

    @PatchMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    Comment update(@PathVariable Long id, @RequestBody Comment comment);

    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void remove(Long id);
}
