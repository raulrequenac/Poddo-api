package com.poddo.commentservice.dto;

import com.poddo.commentservice.model.Comment;

import javax.validation.constraints.NotNull;

public class CommentDto {
    @NotNull
    private Long userId;
    @NotNull
    private String text;
    private Long responseTo;

    public CommentDto(@NotNull Long userId, @NotNull String text, Long responseTo) {
        this.userId = userId;
        this.text = text;
        this.responseTo = responseTo;
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

    public Long getResponseTo() {
        return responseTo;
    }

    public void setResponseTo(Long responseTo) {
        this.responseTo = responseTo;
    }
}
