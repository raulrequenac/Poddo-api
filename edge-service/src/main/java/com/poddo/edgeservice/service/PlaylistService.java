package com.poddo.edgeservice.service;

import com.poddo.edgeservice.clients.PodcastClient;
import com.poddo.edgeservice.model.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    private PodcastClient podcastClient;

    public List<Playlist> findAll() {
        return podcastClient.findAllPlaylists();
    }

    public Playlist findById(String id) {
        return podcastClient.findPlaylistById(id);
    }

    public Playlist create(Playlist playlist) {
        return podcastClient.createPlaylist(playlist);
    }

    public Playlist addPodcast(String id, String podcastId) {
        return podcastClient.addPodcastToPlaylist(id, podcastId);
    }

    public Playlist removePodcast(String id, String podcastId) {
        return podcastClient.removePodcastFromPlaylist(id, podcastId);
    }

    public Playlist update(String id, Playlist playlist) {
        return podcastClient.updatePlaylist(id, playlist);
    }

    public void remove(String id) {
        podcastClient.removePlaylist(id);
    }
}
