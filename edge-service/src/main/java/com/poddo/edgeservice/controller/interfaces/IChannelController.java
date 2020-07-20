package com.poddo.edgeservice.controller.interfaces;

import com.poddo.edgeservice.dto.ChannelDto;
import com.poddo.edgeservice.model.Channel;

import java.util.List;

public interface IChannelController {
    List<Channel> findAll(String name);
    Channel findById(Long id);
    List<String> getPodcasts(Long id);
    List<String> getPlaylists(Long id);
    Channel create(ChannelDto channelDto);
    Channel block(Long id);
    Channel unblock(Long id);
    Channel addPodcast(Long id, String podcastId);
    Channel removePodcast(Long id, String podcastId);
    Channel addPlaylist(Long id, String playlistId);
    Channel removePlaylist(Long id, String playlistId);
    void remove(Long id);
}
