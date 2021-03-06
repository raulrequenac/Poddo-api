package com.poddo.edgeservice.controller.impl;

import com.poddo.edgeservice.controller.interfaces.IChannelController;
import com.poddo.edgeservice.model.Playlist;
import com.poddo.edgeservice.model.User;
import com.poddo.edgeservice.service.ChannelService;
import com.poddo.edgeservice.viewModel.ChannelView;
import com.poddo.edgeservice.viewModel.PodcastView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ChannelController implements IChannelController {
    @Autowired
    private ChannelService channelService;

    @GetMapping("/channels")
    @ResponseStatus(HttpStatus.OK)
    public List<ChannelView> findAll(@RequestParam(value = "name", required = false) String name) {
        return channelService.findAll(name);
    }

    @GetMapping("/channels/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ChannelView findById(@PathVariable Long id) {
        return channelService.findById(id);
    }

    @GetMapping("/channels/{id}/podcasts")
    @ResponseStatus(HttpStatus.OK)
    public List<PodcastView> getPodcasts(@PathVariable Long id) {
        return channelService.getPodcasts(id);
    }

    @GetMapping("/channels/{id}/playlists")
    @ResponseStatus(HttpStatus.OK)
    public List<Playlist> getPlaylists(@PathVariable Long id) {
        return channelService.getPlaylists(id);
    }

    @PostMapping("/channels/{id}/block")
    @ResponseStatus(HttpStatus.OK)
    public ChannelView block(@PathVariable Long id) {
        return channelService.block(id);
    }

    @PostMapping("/channels/{id}/unblock")
    @ResponseStatus(HttpStatus.OK)
    public ChannelView unblock(@PathVariable Long id) {
        return channelService.unblock(id);
    }

    @PostMapping(value = "/channels/{id}/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ChannelView updateLogo(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return channelService.updateLogo(id, file);
    }

    @PostMapping("/channels/{id}/subscribe")
    @ResponseStatus(HttpStatus.OK)
    public ChannelView subscribe(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return channelService.subscribe(id, user);
    }

    @PostMapping("/channels/{id}/unsubscribe")
    @ResponseStatus(HttpStatus.OK)
    public ChannelView unsubscribe(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return channelService.unsubscribe(id, user);
    }
}
