package com.poddo.podcastservice.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String id;
    private String title;
    private String description;
    private List<Podcast> podcasts;

    public Playlist(String title, String description) {
        this.title = title;
        this.description = description;
        this.podcasts = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<Podcast> getPodcasts() {
        return podcasts;
    }

    public void addPodcast(Podcast podcast) {
        this.podcasts.add(podcast);
    }

    public void removePodcast(Podcast podcast) {
        this.podcasts.remove(podcast);
    }
}
