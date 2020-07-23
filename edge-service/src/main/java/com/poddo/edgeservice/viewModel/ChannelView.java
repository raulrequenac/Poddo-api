package com.poddo.edgeservice.viewModel;

import com.poddo.edgeservice.enums.ChannelStatus;
import com.poddo.edgeservice.model.Playlist;

import java.util.List;

public class ChannelView {
    private Long id;
    private String name;
    private String logo;
    private ChannelStatus status;
    private List<Long> subscribers;
    private List<PodcastView> podcasts;
    private List<Playlist> playlists;

    public ChannelView(Long id, String name, String logo, ChannelStatus status, List<Long> subscribers, List<PodcastView> podcasts, List<Playlist> playlists) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.status = status;
        this.subscribers = subscribers;
        this.podcasts = podcasts;
        this.playlists = playlists;
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

    public List<PodcastView> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<PodcastView> podcasts) {
        this.podcasts = podcasts;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}
