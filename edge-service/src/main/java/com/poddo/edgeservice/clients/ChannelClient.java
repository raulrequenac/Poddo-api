package com.poddo.edgeservice.clients;

import com.poddo.edgeservice.model.Channel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "channel-service", url = "http://localhost:8084")
public interface ChannelClient {
    @GetMapping("/channels")
    @ResponseStatus(HttpStatus.OK)
    List<Channel> findAll(@RequestParam(value = "name", required = false) String name);

    @GetMapping("/channels/{id}")
    @ResponseStatus(HttpStatus.OK)
    Channel findById(@PathVariable Long id);

    @GetMapping("/channels/{id}/podcasts")
    @ResponseStatus(HttpStatus.OK)
    List<String> getPodcasts(@PathVariable Long id);

    @GetMapping("/channels/{id}/playlists")
    @ResponseStatus(HttpStatus.OK)
    List<String> getPlaylists(@PathVariable Long id);

    @PostMapping("/channels")
    @ResponseStatus(HttpStatus.CREATED)
    Channel create(@RequestBody Channel channel);

    @PostMapping("/channels/{id}/block")
    @ResponseStatus(HttpStatus.OK)
    Channel block(@PathVariable Long id);

    @PostMapping("/channels/{id}/unblock")
    @ResponseStatus(HttpStatus.OK)
    Channel unblock(@PathVariable Long id);

    @PostMapping("/channels/{id}/add/podcast/{podcastId}")
    @ResponseStatus(HttpStatus.OK)
    Channel addPodcast(@PathVariable Long id, @PathVariable String podcastId);

    @PostMapping("/channels/{id}/remove/podcast/{podcastId}")
    @ResponseStatus(HttpStatus.OK)
    Channel removePodcast(@PathVariable Long id, @PathVariable String podcastId);

    @PostMapping("/channels/{id}/add/playlist/{playlistId}")
    @ResponseStatus(HttpStatus.OK)
    Channel addPlaylist(@PathVariable Long id, @PathVariable String playlistId);

    @PostMapping("/channels/{id}/remove/playlist/{playlistId}")
    @ResponseStatus(HttpStatus.OK)
    Channel removePlaylist(@PathVariable Long id, @PathVariable String playlistId);

    @PostMapping("/channels/{id}/add/subscriber")
    @ResponseStatus(HttpStatus.OK)
    public Channel addSubscriber(@PathVariable Long id);

    @PostMapping("/channels/{id}/remove/subscriber")
    @ResponseStatus(HttpStatus.OK)
    public Channel removeSubscriber(@PathVariable Long id);

    @DeleteMapping("/channels/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void remove(@PathVariable Long id);

    @PostMapping("/channels/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    Channel updateLogo(@PathVariable Long id, @RequestBody String file);
}