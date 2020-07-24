package com.poddo.commentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Comment model
 */
@Entity
public class Comment {
    /**
     * Id of the Comment
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * User ID of the Comment
     */
    @NotNull
    private Long userId;
    /**
     * Text of the Comment
     */
    @NotNull
    private String text;
    /**
     * Stars of the Comment
     */
    private Long stars;
    /**
     * Date of creation of the Comment
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate createdAt;
    /**
     * Comment to respond
     */
    @ManyToOne
    @JsonIgnore
    private Comment responseTo;
    /**
     * Responses to the Comment
     */
    @OneToMany(mappedBy = "responseTo")
    private List<Comment> responses;

    /**
     * Empty Constructor
     */
    public Comment() {
    }

    /**
     * Constructor
     * @param userId User ID of the Comment
     * @param text Text of the Comment
     * @param responseTo Comment to respond
     */
    public Comment(Long userId, String text, Comment responseTo) {
        this.userId = userId;
        this.text = text;
        this.stars = (long) 0;
        this.responseTo = responseTo;
        this.createdAt = LocalDate.now();
        this.responses = responseTo == null ? new ArrayList<>() : null;
    }

    /**
     * Get ID of the Comment
     * @return ID of the Comment
     */
    public Long getId() {
        return id;
    }

    /**
     * Set ID of the Comment
     * @param id ID of the Comment
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get User ID of the Comment
     * @return User ID of the Comment
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Set User ID of the Comment
     * @param userId USer ID of the Comment
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Get Text of the Comment
     * @return Text of the Comment
     */
    public String getText() {
        return text;
    }

    /**
     * Set Text of the Comment
     * @param text Text of the Comment
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get Stars of the Comment
     * @return Stars of the Comment
     */
    public Long getStars() {
        return stars;
    }

    /**
     * Add star to the Comment
     */
    public void addStar() {
        this.stars++;
    }

    /**
     * Get Date of creation of the Comment
     * @return Date of creation of the Comment
     */
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    /**
     * Set Date of creation of the Comment
     * @param createdAt Date of creation of the Comment
     */
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Get Comment to respond
     * @return Comment to respond
     */
    public Comment getResponseTo() {
        return responseTo;
    }

    /**
     * Set Comment to respond
     * @param responseTo Comment to respond
     */
    public void setResponseTo(Comment responseTo) {
        this.responseTo = responseTo;
    }

    /**
     * Get Responses to the Comment
     * @return Responses to the Comment
     */
    public List<Comment> getResponses() {
        return responses;
    }

    /**
     * Set Responses to the Comment
     * @param responses Responses to the Comment
     */
    public void setResponses(List<Comment> responses) {
        this.responses = responses;
    }
}
