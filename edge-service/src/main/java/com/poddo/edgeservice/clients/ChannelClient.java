package com.poddo.edgeservice.clients;

import com.poddo.edgeservice.model.Channel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Channel client
 */
@FeignClient(name = "channel-service", url = "http://localhost:8084")
public interface ChannelClient {
    /**
     * Find all Channels
     * @param name Name of Channel
     * @return List of Channel
     */
    @GetMapping("/channels")
    @ResponseStatus(HttpStatus.OK)
    List<Channel> findAll(@RequestParam(value = "name", required = false) String name);

    /**
     * Find Channel by ID
     * @param id ID of Channel
     * @return Channel
     */
    @GetMapping("/channels/{id}")
    @ResponseStatus(HttpStatus.OK)
    Channel findById(@PathVariable Long id);

    /**
     * Get Podcasts of Channel
     * @param id Id of Channel
     * @return List of Podcasts
     */
    @GetMapping("/channels/{id}/podcasts")
    @ResponseStatus(HttpStatus.OK)
    List<String> getPodcasts(@PathVariable Long id);

    /**
     * Get Playlists of Channel
     * @param id ID of Channel
     * @return Lists of Playlists
     */
    @GetMapping("/channels/{id}/playlists")
    @ResponseStatus(HttpStatus.OK)
    List<String> getPlaylists(@PathVariable Long id);

    /**
     * Create Channel
     * @param channel Channel to create
     * @return Channel created
     */
    @PostMapping("/channels")
    @ResponseStatus(HttpStatus.CREATED)
    Channel create(@RequestBody Channel channel);

    /**
     * Block Channel
     * @param id ID of Channel to block
     * @return Channel blocked
     */
    @PostMapping("/channels/{id}/block")
    @ResponseStatus(HttpStatus.OK)
    Channel block(@PathVariable Long id);

    /**
     * Unlock Channel
     * @param id ID of Channel to unlock
     * @return Channel Unlocked
     */
    @PostMapping("/channels/{id}/unblock")
    @ResponseStatus(HttpStatus.OK)
    Channel unblock(@PathVariable Long id);

    /**
     * Add Podcast to Channel
     * @param id ID of Channel
     * @param podcastId ID of Podcast
     * @return Channel
     */
    @PostMapping("/channels/{id}/add/podcast/{podcastId}")
    @ResponseStatus(HttpStatus.OK)
    Channel addPodcast(@PathVariable Long id, @PathVariable String podcastId);

    /**
     * Remove Podcast from Channel
     * @param id ID of Channel
     * @param podcastId ID of Podcast
     * @return Channel
     */
    @PostMapping("/channels/{id}/remove/podcast/{podcastId}")
    @ResponseStatus(HttpStatus.OK)
    Channel removePodcast(@PathVariable Long id, @PathVariable String podcastId);

    /**
     * Add Playlist to Channel
     * @param id ID of Channel
     * @param playlistId ID of Playlist
     * @return Channel
     */
    @PostMapping("/channels/{id}/add/playlist/{playlistId}")
    @ResponseStatus(HttpStatus.OK)
    Channel addPlaylist(@PathVariable Long id, @PathVariable String playlistId);

    /**
     * Remove Playlist from Channel
     * @param id Id of Channel
     * @param playlistId ID of Playlist
     * @return Channel
     */
    @PostMapping("/channels/{id}/remove/playlist/{playlistId}")
    @ResponseStatus(HttpStatus.OK)
    Channel removePlaylist(@PathVariable Long id, @PathVariable String playlistId);

    /**
     * Add subscriber to Channel
     * @param id ID of Channel
     * @return Channel
     */
    @PostMapping("/channels/{id}/add/subscriber")
    @ResponseStatus(HttpStatus.OK)
    public Channel addSubscriber(@PathVariable Long id);

    /**
     * Remove Subscriber from Channel
     * @param id ID of Channel
     * @return Channel
     */
    @PostMapping("/channels/{id}/remove/subscriber")
    @ResponseStatus(HttpStatus.OK)
    public Channel removeSubscriber(@PathVariable Long id);

    /**
     * Remove Channel
     * @param id Id of Channel to remove
     */
    @DeleteMapping("/channels/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void remove(@PathVariable Long id);

    /**
     * Update Logo of Channel
     * @param id ID of Channel
     * @param file Logo update
     * @return Channel
     */
    @PostMapping("/channels/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    Channel updateLogo(@PathVariable Long id, @RequestBody String file);
}