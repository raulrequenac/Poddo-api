package com.poddo.edgeservice.controller.impl;

import com.poddo.edgeservice.controller.interfaces.IChannelController;
import com.poddo.edgeservice.dto.ChannelDto;
import com.poddo.edgeservice.model.Channel;
import com.poddo.edgeservice.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChannelController implements IChannelController {
    @Autowired
    private ChannelService channelService;

    @GetMapping("/channels")
    @ResponseStatus(HttpStatus.OK)
    public List<Channel> findAll(@RequestParam(value = "name", required = false) String name) {
        return channelService.findAll(name);
    }

    @GetMapping("/channels/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Channel findById(@PathVariable Long id) {
        return channelService.findById(id);
    }

    @GetMapping("/channels/{id}/podcasts")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getPodcasts(@PathVariable Long id) {
        return channelService.getPodcasts(id);
    }

    @GetMapping("/channels/{id}/playlists")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getPlaylists(@PathVariable Long id) {
        return channelService.getPlaylists(id);
    }

    @PostMapping("/channels")
    @ResponseStatus(HttpStatus.CREATED)
    public Channel create(@RequestBody ChannelDto channelDto) {
        return channelService.create(channelDto);
    }

    @PostMapping("/channels/{id}/block")
    @ResponseStatus(HttpStatus.OK)
    public Channel block(@PathVariable Long id) {
        return channelService.block(id);
    }

    @PostMapping("/channels/{id}/block")
    @ResponseStatus(HttpStatus.OK)
    public Channel unblock(@PathVariable Long id) {
        return channelService.unblock(id);
    }

    @PostMapping("/channels/{id}/add/podcast/{podcastId}")
    @ResponseStatus(HttpStatus.OK)
    public Channel addPodcast(@PathVariable Long id, @PathVariable String podcastId) {
        return channelService.addPodcast(id, podcastId);
    }

    @PostMapping("/channels/{id}/remove/podcast/{podcastId}")
    @ResponseStatus(HttpStatus.OK)
    public Channel removePodcast(@PathVariable Long id, @PathVariable String podcastId) {
        return channelService.removePodcast(id, podcastId);
    }

    @PostMapping("/channels/{id}/add/playlist/{playlistId}")
    @ResponseStatus(HttpStatus.OK)
    public Channel addPlaylist(@PathVariable Long id, @PathVariable String playlistId) {
        return channelService.addPlaylist(id, playlistId);
    }

    @PostMapping("/channels/{id}/remove/playlist/{playlistId}")
    @ResponseStatus(HttpStatus.OK)
    public Channel removePlaylist(@PathVariable Long id, @PathVariable String playlistId) {
        return channelService.removePlaylist(id, playlistId);
    }

    @DeleteMapping("/channels/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        channelService.remove(id);
    }
}
