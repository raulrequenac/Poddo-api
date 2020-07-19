package com.poddo.podcastservice.service;

import com.poddo.podcastservice.exceptions.IdNotFoundException;
import com.poddo.podcastservice.model.Playlist;
import com.poddo.podcastservice.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private PodcastService podcastService;

    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    public Playlist findById(Long id) {
        return playlistRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Playlist with id "+id+" not found."));
    }

    public Playlist create(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public Playlist addPodcast(Long id, Long podcastId) {
        Playlist playlist = findById(id);
        playlist.addPodcast(podcastService.findById(podcastId));

        return playlist;
    }

    public Playlist removePodcast(Long id, Long podcastId) {
        Playlist playlist = findById(id);
        playlist.removePodcast(podcastService.findById(podcastId));

        return playlist;
    }

    public Playlist update(Long id, Playlist playlistUpdate) {
        Playlist playlist = findById(id);

        if (playlistUpdate.getTitle()!=null) playlist.setTitle(playlistUpdate.getTitle());
        if (playlistUpdate.getDescription()!=null) playlist.setDescription(playlistUpdate.getDescription());

        return playlist;
    }

    public void remove(Long id) {
        playlistRepository.deleteById(id);
    }
}
