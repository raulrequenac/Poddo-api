package com.poddo.edgeservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.poddo.edgeservice.clients.PodcastClient;
import com.poddo.edgeservice.enums.Role;
import com.poddo.edgeservice.exceptions.PlaylistServiceException;
import com.poddo.edgeservice.model.Playlist;
import com.poddo.edgeservice.model.User;
import com.poddo.edgeservice.viewModel.ChannelView;
import com.poddo.edgeservice.viewModel.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    private PodcastClient podcastClient;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private UserService userService;

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public List<Playlist> findAll() {
        return podcastClient.findAllPlaylists();
    }

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public Playlist findById(String id) {
        return podcastClient.findPlaylistById(id);
    }

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public Playlist create(User auth, Playlist playlist, Long channelId) {
        UserView user = userService.findByUsername(auth.getUsername());
        if (!user.getChannel().getId().equals(channelId)) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        Playlist pl = podcastClient.createPlaylist(playlist);
        channelService.addPlaylist(channelId, pl.getId());

        return pl;
    }

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public Playlist addPodcast(User auth, String id, String podcastId, Long channelId) {
        UserView user = userService.findByUsername(auth.getUsername());
        if (!user.getChannel().getId().equals(channelId)) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        return podcastClient.addPodcastToPlaylist(id, podcastId);
    }

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public Playlist removePodcast(User auth, String id, String podcastId, Long channelId) {
        UserView user = userService.findByUsername(auth.getUsername());
        if (!user.getChannel().getId().equals(channelId)) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        return podcastClient.removePodcastFromPlaylist(id, podcastId);
    }

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public Playlist update(User auth, String id, Playlist playlist) {
        UserView u = userService.findByUsername(auth.getUsername());
        if (!u.getChannel().getPlaylists().contains(findById(id)))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        return podcastClient.updatePlaylist(id, playlist);
    }

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public void remove(User auth, String id) {
        UserView u = userService.findByUsername(auth.getUsername());
        ChannelView channel = u.getChannel();
        if (!auth.getRole().equals(Role.ADMIN) && !channel.getPlaylists().contains(findById(id)))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        channelService.removePlaylist(channel.getId(), id);
        podcastClient.removePlaylist(id);
    }

    public List<Playlist> findAllFallback() {
        throw new PlaylistServiceException("findAll");
    }

    public Playlist findByIdFallback(String id) {
        throw new PlaylistServiceException("findById");
    }

    public Playlist createFallback(Playlist playlist) {
        throw new PlaylistServiceException("create");
    }

    public Playlist addPodcastFallback(String id, String podcastId) {
        throw new PlaylistServiceException("addPodcast");
    }

    public Playlist removePodcastFallback(String id, String podcastId) {
        throw new PlaylistServiceException("removePodcast");
    }

    public Playlist updateFallback(String id, Playlist playlist) {
        throw new PlaylistServiceException("update");
    }

    public void removeFallback(String id) {
        throw new PlaylistServiceException("remove");
    }
}
