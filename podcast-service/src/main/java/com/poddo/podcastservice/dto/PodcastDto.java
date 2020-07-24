package com.poddo.podcastservice.dto;

import com.poddo.podcastservice.enums.Status;
import com.poddo.podcastservice.enums.Tags;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PodcastDto {
    private List<Tags> tags;
    private String title;
    private String description;
    private Status status;
    private Boolean allowComments;
    private Long channelId;

    public PodcastDto(List<Tags> tags, String title, String description, Status status, boolean allowComments, Long channelId) {
        this.tags = tags;
        this.title = title;
        this.description = description;
        this.status = status;
        this.allowComments = allowComments;
        this.channelId = channelId;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean allowsComments() {
        return allowComments;
    }

    public void setAllowComments(boolean allowComments) {
        this.allowComments = allowComments;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }
}
