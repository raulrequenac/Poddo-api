package com.poddo.edgeservice.controller.interfaces;

import com.poddo.edgeservice.model.Playlist;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IPlaylistController {
    List<Playlist> findAll();
    Playlist findById(String id);
    Playlist create(Playlist playlist);
    Playlist addPodcast(String id, String podcastId);
    Playlist removePodcast(String id, String podcastId);
    Playlist update(String id, Playlist playlist);
    void remove(String id);
}
