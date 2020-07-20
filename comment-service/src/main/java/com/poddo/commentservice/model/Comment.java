package com.poddo.commentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private String text;
    private Long stars;
    private LocalDateTime createdAt;
    @ManyToOne
    @JsonIgnore
    private Comment responseTo;
    @OneToMany(mappedBy = "responseTo")
    private List<Comment> responses;

    public Comment(Long userId, String text, Comment responseTo) {
        this.userId = userId;
        this.text = text;
        this.stars = (long) 0;
        this.responseTo = responseTo;
        this.createdAt = LocalDateTime.now();
        this.responses = null;
    }

    public Comment(Long userId, String text) {
        this.userId = userId;
        this.text = text;
        this.stars = (long) 0;
        this.responseTo = null;
        this.createdAt = LocalDateTime.now();
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

    public void addStar() {
        this.stars++;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
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
