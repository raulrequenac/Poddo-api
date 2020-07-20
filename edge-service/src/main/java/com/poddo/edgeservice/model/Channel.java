package com.poddo.edgeservice.model;

import com.poddo.edgeservice.enums.ChannelStatus;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Channel {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String logo;
    private ChannelStatus status;
    @NotNull
    private Long userId;
    private List<Long> subscribers;
    private List<String> podcasts;
    private List<String> playlists;

    public Channel(@NotNull String name, @NotNull String logo, @NotNull Long userId) {
        this.name = name;
        this.logo = logo;
        this.userId = userId;
        this.status = ChannelStatus.UNLOCKED;
        this.subscribers = new ArrayList<>();
        this.podcasts = new ArrayList<>();
        this.playlists = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public ChannelStatus getStatus() {
        return status;
    }

    public void setStatus(ChannelStatus status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Long> subscribers) {
        this.subscribers = subscribers;
    }

    public List<String> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<String> podcasts) {
        this.podcasts = podcasts;
    }

    public List<String> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<String> playlists) {
        this.playlists = playlists;
    }
}
