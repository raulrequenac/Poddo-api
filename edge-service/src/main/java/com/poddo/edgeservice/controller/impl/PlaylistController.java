package com.poddo.edgeservice.controller.impl;

import com.poddo.edgeservice.controller.interfaces.IPlaylistController;
import com.poddo.edgeservice.model.Playlist;
import com.poddo.edgeservice.model.User;
import com.poddo.edgeservice.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PostMapping("/playlists/{channelId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Playlist create(@AuthenticationPrincipal User auth, @RequestBody Playlist playlist, @PathVariable Long channelId) {
        return playlistService.create(auth, playlist, channelId);
    }

    @PostMapping("/playlists/{id}/add/{podcastId}/to/{channelId}")
    @ResponseStatus(HttpStatus.OK)
    public Playlist addPodcast(@AuthenticationPrincipal User auth,
                               @PathVariable String id,
                               @PathVariable String podcastId,
                               @PathVariable Long channelId) {
        return playlistService.addPodcast(auth, id, podcastId, channelId);
    }

    @PostMapping("/playlists/{id}/remove/{podcastId}/from/{channelId}")
    @ResponseStatus(HttpStatus.OK)
    public Playlist removePodcast(@AuthenticationPrincipal User auth,
                                  @PathVariable String id,
                                  @PathVariable String podcastId,
                                  @PathVariable Long channelId) {
        return playlistService.removePodcast(auth, id, podcastId, channelId);
    }

    @PatchMapping("/playlists/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Playlist update(@AuthenticationPrincipal User auth, @PathVariable String id, @RequestBody Playlist playlist) {
        return playlistService.update(auth, id, playlist);
    }

    @DeleteMapping("/playlists/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@AuthenticationPrincipal User auth, @PathVariable String id) {
        playlistService.remove(auth, id);
    }
}
