package com.poddo.podcastservice.model;

import com.poddo.podcastservice.enums.Status;
import com.poddo.podcastservice.enums.Tags;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Podcast {
    private String id;
    @NotNull
    private Long stars;
    @NotNull
    private List<Tags> tags;
    @NotNull
    private String title;
    private String description;
    private List<Long> comments;
    @NotNull
    private Status status;
    @NotNull
    private String audio;
    @NotNull
    private boolean allowComments;
    private LocalDate creationDate;

    public Podcast() {
    }

    public Podcast(List<Tags> tags, String title, String description, Status status, boolean allowComments) {
        this.tags = tags;
        this.title = title;
        this.description = description;
        this.status = status;
        this.audio = "";
        this.allowComments = allowComments;
        this.comments = new ArrayList<>();
        this.stars = (long) 0;
        this.creationDate = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getStars() {
        return stars;
    }

    public void addStar() {
        this.stars++;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getComments() {
        return comments;
    }

    public void addComment(Long comment) {
        this.comments.add(comment);
    }

    public void removeComment(Long comment) {
        this.comments.remove(comment);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public boolean allowsComments() {
        return allowComments;
    }

    public void setAllowComments(boolean allowComments) {
        this.allowComments = allowComments;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
