package com.poddo.channelservice.service;

import com.poddo.channelservice.exceptions.IdNotFoundException;
import com.poddo.channelservice.model.Channel;
import com.poddo.channelservice.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;

    public List<Channel> findAll(String name) {
        return name==null ? channelRepository.findAll() : channelRepository.findByNameLikeOrderBySubscribersCount(name);
    }

    public Channel findById(Long id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Channel with id "+id+" not found."));
    }

    public List<String> getPodcasts(Long id) {
        Channel channel = findById(id);
        return channel.getPodcasts();
    }

    public List<String> getPlaylists(Long id) {
        Channel channel = findById(id);
        return channel.getPlaylists();
    }

    public Channel create(Channel channel) {
        System.out.println(channel.getId());
        Channel c = new Channel(channel.getId(), channel.getName());
        System.out.println(c.getId());
        return channelRepository.save(c);
    }

    public Channel block(Long id) {
        Channel channel = findById(id);
        channel.block();
        return channelRepository.save(channel);
    }

    public Channel unblock(Long id) {
        Channel channel = findById(id);
        channel.unblock();
        return channelRepository.save(channel);
    }

    public Channel addPodcast(Long id, String podcastId) {
        Channel channel = findById(id);
        channel.addPodcast(podcastId);
        return channelRepository.save(channel);
    }

    public Channel removePodcast(Long id, String podcastId) {
        Channel channel = findById(id);
        channel.removePodcast(podcastId);
        return channelRepository.save(channel);
    }

    public Channel addPlaylist(Long id, String playlistId) {
        Channel channel = findById(id);
        channel.addPlaylist(playlistId);
        return channelRepository.save(channel);
    }

    public Channel removePlaylist(Long id, String playlistId) {
        Channel channel = findById(id);
        channel.removePlaylist(playlistId);
        return channelRepository.save(channel);
    }

    public void remove(Long id) {
        channelRepository.deleteById(id);
    }

    public Channel updateLogo(Long id, String file) {
        Channel channel = findById(id);
        channel.setLogo(file);

        return channelRepository.save(channel);
    }
}
