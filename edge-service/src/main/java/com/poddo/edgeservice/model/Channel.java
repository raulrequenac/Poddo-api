package com.poddo.edgeservice.model;

import com.poddo.edgeservice.enums.ChannelStatus;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Channel {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    private String logo;
    private ChannelStatus status;
    private List<Long> subscribers;
    private List<String> podcasts;
    private List<String> playlists;

    public Channel() {
    }

    public Channel(Long id, String name) {
        this.id = id;
        this.name = name;
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
