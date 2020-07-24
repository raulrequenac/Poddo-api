package com.poddo.edgeservice.viewModel;

import com.poddo.edgeservice.enums.PodcastStatus;
import com.poddo.edgeservice.enums.Tags;
import com.poddo.edgeservice.model.Channel;
import com.poddo.edgeservice.model.Comment;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class PodcastView {
    private String id;
    private Long stars;
    private List<Tags> tags;
    private String title;
    private String description;
    private List<Comment> comments;
    private PodcastStatus status;
    private String audio;
    private Boolean allowComments;
    private LocalDate creationDate;
    private Long channelId;

    public PodcastView(String id, Long stars, List<Tags> tags, String title, String description, List<Comment> comments, PodcastStatus status, String audio, Boolean allowComments, LocalDate creationDate, Long channelId) {
        this.id = id;
        this.stars = stars;
        this.tags = tags;
        this.title = title;
        this.description = description;
        this.comments = comments;
        this.status = status;
        this.audio = audio;
        this.allowComments = allowComments;
        this.creationDate = creationDate;
        this.channelId = channelId;
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

    public void setStars(Long stars) {
        this.stars = stars;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public PodcastStatus getStatus() {
        return status;
    }

    public void setStatus(PodcastStatus status) {
        this.status = status;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public Boolean getAllowComments() {
        return allowComments;
    }

    public void setAllowComments(Boolean allowComments) {
        this.allowComments = allowComments;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }
}
