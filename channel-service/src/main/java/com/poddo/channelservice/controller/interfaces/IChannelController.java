package com.poddo.channelservice.controller.interfaces;

import com.poddo.channelservice.model.Channel;

import java.util.List;

public interface IChannelController {
    List<Channel> findAll(String name);
    Channel findById(Long id);
    List<String> getPodcasts(Long id);
    List<String> getPlaylists(Long id);
    Channel create(Channel channel);
    Channel block(Long id);
    Channel unblock(Long id);
    Channel addPodcast(Long id, String podcastId);
    Channel removePodcast(Long id, String podcastId);
    Channel addPlaylist(Long id, String playlistId);
    Channel removePlaylist(Long id, String playlistId);
    void remove(Long id);
    Channel updateLogo(Long id, String file);
}
