package com.poddo.edgeservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.poddo.edgeservice.clients.ChannelClient;
import com.poddo.edgeservice.dto.ChannelDto;
import com.poddo.edgeservice.exceptions.ChannelServiceException;
import com.poddo.edgeservice.model.Channel;
import com.poddo.edgeservice.model.Playlist;
import com.poddo.edgeservice.model.User;
import com.poddo.edgeservice.viewModel.ChannelView;
import com.poddo.edgeservice.viewModel.PodcastView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChannelService {
    @Autowired
    private ChannelClient channelClient;
    @Autowired
    private PodcastService podcastService;
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private UserService userService;

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public List<ChannelView> findAll(String name) {
        return channelClient.findAll(name).stream().map(this::convertToView).collect(Collectors.toList());
    }

    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public ChannelView findById(Long id) {
        return convertToView(channelClient.findById(id));
    }

    @HystrixCommand(fallbackMethod = "getPodcastsFallback")
    public List<PodcastView> getPodcasts(Long id) {
        return channelClient.getPodcasts(id)
                .stream()
                .map(podcastId -> podcastService.findById(podcastId))
                .collect(Collectors.toList());
    }

    @HystrixCommand(fallbackMethod = "getPlaylistsFallback")
    public List<Playlist> getPlaylists(Long id) {
        return channelClient.getPlaylists(id)
                .stream()
                .map(playListId -> playlistService.findById(playListId))
                .collect(Collectors.toList());
    }

    @HystrixCommand(fallbackMethod = "createFallback")
    public ChannelView create(ChannelDto channelDto) {
        return convertToView(channelClient.create(channelDto));
    }

    @HystrixCommand(fallbackMethod = "blockFallback")
    public ChannelView block(Long id) {
        return convertToView(channelClient.block(id));
    }

    @HystrixCommand(fallbackMethod = "unblockFallback")
    public ChannelView unblock(Long id) {
        return convertToView(channelClient.unblock(id));
    }

    @HystrixCommand(fallbackMethod = "addPodcastFallback")
    public ChannelView addPodcast(Long id, String podcastId) {
        return convertToView(channelClient.addPodcast(id, podcastId));
    }

    @HystrixCommand(fallbackMethod = "removePodcastFallback")
    public ChannelView removePodcast(Long id, String podcastId) {
        return convertToView(channelClient.removePodcast(id, podcastId));
    }

    @HystrixCommand(fallbackMethod = "addPlaylistFallback")
    public ChannelView addPlaylist(Long id, String playlistId) {
        return convertToView(channelClient.addPlaylist(id, playlistId));
    }

    @HystrixCommand(fallbackMethod = "removePlaylistFallback")
    public ChannelView removePlaylist(Long id, String playlistId) {
        return convertToView(channelClient.removePlaylist(id, playlistId));
    }

    @HystrixCommand(fallbackMethod = "removeFallback")
    public void remove(Long id) {
        channelClient.remove(id);
    }

    public ChannelView convertToView(Channel channel) {
        List<PodcastView> podcasts = channel.getPodcasts().stream().map(podcastId -> podcastService.findById(podcastId)).collect(Collectors.toList());
        List<Playlist> playlists = channel.getPlaylists().stream().map(playlistId -> playlistService.findById(playlistId)).collect(Collectors.toList());

        return new ChannelView(channel.getId(), channel.getName(), channel.getLogo(), channel.getStatus(), channel.getUserId(), channel.getSubscribers(), podcasts, playlists);
    }

    public List<ChannelView> findAllFallback(String name) {
        throw new ChannelServiceException("findAll");
    }

    public ChannelView findByIdFallback(Long id) {
        throw new ChannelServiceException("findById");
    }

    public List<PodcastView> getPodcastsFallback(Long id) {
        throw new ChannelServiceException("getPodcasts");
    }

    public List<Playlist> getPlaylistsFallback(Long id) {
        throw new ChannelServiceException("getPlaylists");
    }

    public ChannelView createFallback(ChannelDto channelDto) {
        throw new ChannelServiceException("create");
    }

    public ChannelView blockFallback(Long id) {
        throw new ChannelServiceException("block");
    }

    public ChannelView unblockFallback(Long id) {
        throw new ChannelServiceException("unblock");
    }

    public ChannelView addPodcastFallback(Long id, String podcastId) {
        throw new ChannelServiceException("addPodcast");
    }

    public ChannelView removePodcastFallback(Long id, String podcastId) {
        throw new ChannelServiceException("removePodcast");
    }

    public ChannelView addPlaylistFallback(Long id, String playlistId) {
        throw new ChannelServiceException("addPlaylist");
    }

    public ChannelView removePlaylistFallback(Long id, String playlistId) {
        throw new ChannelServiceException("removePlaylist");
    }

    public void removeFallback(Long id) {
        throw new ChannelServiceException("remove");
    }
}
