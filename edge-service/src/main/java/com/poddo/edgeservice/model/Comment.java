package com.poddo.edgeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Comment {
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private String text;
    private Long stars;
    private LocalDate createdAt;
    private Comment responseTo;
    private List<Comment> responses;

    public Comment() {
    }

    public Comment(Long userId, String text, Comment responseTo) {
        this.userId = userId;
        this.text = text;
        this.stars = (long) 0;
        this.responseTo = responseTo;
        this.createdAt = LocalDate.now();
        this.responses = null;
    }

    public Comment(Long userId, String text) {
        this.userId = userId;
        this.text = text;
        this.stars = (long) 0;
        this.responseTo = null;
        this.createdAt = LocalDate.now();
        this.responses = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getStars() {
        return stars;
    }

    public void setStars(Long stars) {
        this.stars = stars;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Comment getResponseTo() {
        return responseTo;
    }

    public void setResponseTo(Comment responseTo) {
        this.responseTo = responseTo;
    }

    public List<Comment> getResponses() {
        return responses;
    }

    public void setResponses(List<Comment> responses) {
        this.responses = responses;
    }
}
