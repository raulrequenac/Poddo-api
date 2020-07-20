package com.poddo.edgeservice.model;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String id;
    @NotNull
    private String title;
    @NotNull
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

    public void setPodcasts(List<Podcast> podcasts) {
        this.podcasts = podcasts;
    }
}
