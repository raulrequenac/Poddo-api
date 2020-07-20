package com.poddo.edgeservice.controller.interfaces;

import com.poddo.edgeservice.model.Playlist;
import com.poddo.edgeservice.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IPlaylistController {
    List<Playlist> findAll();
    Playlist findById(String id);
    Playlist create(User auth, Playlist playlist, Long channelId);
    Playlist addPodcast(User auth, String id, String podcastId, Long channelId);
    Playlist removePodcast(User auth, String id, String podcastId, Long channelId);
    Playlist update(User auth, String id, Playlist playlist);
    void remove(User auth, String id);
}
