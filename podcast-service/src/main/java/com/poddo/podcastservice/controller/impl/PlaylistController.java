package com.poddo.podcastservice.controller.impl;

import com.poddo.podcastservice.controller.interfaces.IPlaylistController;
import com.poddo.podcastservice.model.Playlist;
import com.poddo.podcastservice.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlaylistController implements IPlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/playlists")
    @ResponseStatus(HttpStatus.OK)
    public List<Playlist> findAll() {
        return playlistService.findAll();
    }

    @GetMapping("/playlists/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Playlist findById(@PathVariable String id) {
        return playlistService.findById(id);
    }

    @PostMapping("/playlists")
    @ResponseStatus(HttpStatus.CREATED)
    public Playlist create(@RequestBody Playlist playlist) {
        return playlistService.create(playlist);
    }

    @PostMapping("/playlists/{id}/add/{podcastId}")
    @ResponseStatus(HttpStatus.OK)
    public Playlist addPodcast(@PathVariable String id, @PathVariable String podcastId) {
        return playlistService.addPodcast(id, podcastId);
    }

    @PostMapping("/playlists/{id}/remove/{podcastId}")
    @ResponseStatus(HttpStatus.OK)
    public Playlist removePodcast(@PathVariable String id, @PathVariable String podcastId) {
        return playlistService.removePodcast(id, podcastId);
    }

    @PatchMapping("/playlists/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Playlist update(@PathVariable String id, @RequestBody Playlist playlist) {
        return playlistService.update(id, playlist);
    }

    @DeleteMapping("/playlists/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String id) {
        playlistService.remove(id);
    }
}
