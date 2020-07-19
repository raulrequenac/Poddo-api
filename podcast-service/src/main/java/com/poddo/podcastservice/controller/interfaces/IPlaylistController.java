package com.poddo.podcastservice.controller.interfaces;

import com.poddo.podcastservice.model.Playlist;

import java.util.List;

public interface IPlaylistController {
    List<Playlist> findAll();
    Playlist findById(String id);
    Playlist create(Playlist playlist);
    Playlist addPodcast(String id, String podcastId);
    Playlist removePodcast(String id, String podcastId);
}
