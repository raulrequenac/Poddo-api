package com.poddo.edgeservice.service;

import com.poddo.edgeservice.clients.ChannelClient;
import com.poddo.edgeservice.dto.ChannelDto;
import com.poddo.edgeservice.model.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {
    @Autowired
    private ChannelClient channelClient;

    public List<Channel> findAll(String name) {
        return channelClient.findAll(name);
    }

    public Channel findById(Long id) {
        return channelClient.findById(id);
    }

    public List<String> getPodcasts(Long id) {
        return channelClient.getPodcasts(id);
    }

    public List<String> getPlaylists(Long id) {
        return channelClient.getPlaylists(id);
    }

    public Channel create(ChannelDto channelDto) {
        return channelClient.create(channelDto);
    }

    public Channel block(Long id) {
        return channelClient.block(id);
    }

    public Channel unblock(Long id) {
        return channelClient.unblock(id);
    }

    public Channel addPodcast(Long id, String podcastId) {
        return channelClient.addPodcast(id, podcastId);
    }

    public Channel removePodcast(Long id, String podcastId) {
        return channelClient.removePodcast(id, podcastId);
    }

    public Channel addPlaylist(Long id, String playlistId) {
        return channelClient.addPlaylist(id, playlistId);
    }

    public Channel removePlaylist(Long id, String playlistId) {
        return channelClient.removePlaylist(id, playlistId);
    }

    public void remove(Long id) {
        channelClient.remove(id);
    }
}
