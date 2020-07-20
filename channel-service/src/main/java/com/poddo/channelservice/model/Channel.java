package com.poddo.channelservice.model;

import com.poddo.channelservice.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String logo;
    private Status status;
    @NotNull
    private Long userId;
    @ElementCollection
    private List<Long> subscribers;
    @ElementCollection
    private List<String> podcasts;
    @ElementCollection
    private List<String> playlists;

    public Channel(String name, String logo, Long userId) {
        this.name = name;
        this.logo = logo;
        this.status = Status.UNLOCKED;
        this.userId = userId;
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

    public Status getStatus() {
        return status;
    }

    public void block() {
        this.status = Status.LOCKED;
    }

    public void unblock() {
        this.status = Status.UNLOCKED;
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

    public void addSubscriber(Long subscriber) {
        this.subscribers.add(subscriber);
    }

    public void removeSubscriber(Long subscriber) {
        this.subscribers.remove(subscriber);
    }

    public List<String> getPodcasts() {
        return podcasts;
    }

    public void addPodcast(String podcast) {
        this.podcasts.add(podcast);
    }

    public void removePodcast(String podcast) {
        this.podcasts.remove(podcast);
    }

    public List<String> getPlaylists() {
        return playlists;
    }

    public void addPlaylist(String playlist) {
        this.playlists.add(playlist);
    }

    public void removePlaylist(String playlist) {
        this.playlists.remove(playlist);
    }
}
