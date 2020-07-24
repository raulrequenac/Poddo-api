package com.poddo.edgeservice.clients;

import com.poddo.edgeservice.model.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Comment Client
 */
@FeignClient(name = "comment-service", url = "http://localhost:8082")
public interface CommentClient {
    /**
     * Find all Comments
     * @return List of Comments
     */
    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    List<Comment> findAll();

    /**
     * Find Comment by Id
     * @param id Id of Comment
     * @return Comment
     */
    @GetMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    Comment findById(@PathVariable Long id);

    /**
     * Create Comment
     * @param comment Comment to create
     * @return Comment created
     */
    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    Comment create(@RequestBody Comment comment);

    /**
     * Star Comment
     * @param id ID of Comment
     * @return Comment
     */
    @PostMapping("/comments/{id}/star")
    @ResponseStatus(HttpStatus.OK)
    Comment star(@PathVariable Long id);

    /**
     * Update Comment
     * @param id ID of Comment
     * @param comment Comment updates
     * @return Comment updated
     */
    @PatchMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    Comment update(@PathVariable Long id, @RequestBody Comment comment);

    /**
     * REmove Comment
     * @param id ID of Comment
     */
    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void remove(Long id);
}
