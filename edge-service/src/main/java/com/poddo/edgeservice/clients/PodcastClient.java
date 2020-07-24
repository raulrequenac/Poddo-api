package com.poddo.edgeservice.clients;

import com.poddo.edgeservice.dto.PodcastDto;
import com.poddo.edgeservice.model.Playlist;
import com.poddo.edgeservice.model.Podcast;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Podcast Client
 */
@FeignClient(name = "podcast-service", url = "http://localhost:8081")
public interface PodcastClient {
    //PODCAST

    /**
     * Find all Podcasts
     * @return List of Podcasts
     */
    @GetMapping("/podcasts")
    @ResponseStatus(HttpStatus.OK)
    List<Podcast> findAllPodcasts();

    /**
     * Find all Podcasts orderer by stars descendant
     * @param title Title of Podcast
     * @param tag Tags of Podcast
     * @return Podcast
     */
    @GetMapping("/podcasts/stars") //?title=bar
    @ResponseStatus(HttpStatus.OK)
    List<Podcast> findAllOrderByStarsDesc(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "tag", required = false) String tag
    );

    /**
     * Find Podcast by ID
     * @param id ID of Podcast
     * @return Podcast
     */
    @GetMapping("/podcasts/{id}")
    @ResponseStatus(HttpStatus.OK)
    Podcast findPodcastById(@PathVariable String id);

    /**
     * Create Podcast
     * @param podcastDto Podcast to create
     * @return Podcast created
     */
    @PostMapping("/podcasts")
    @ResponseStatus(HttpStatus.CREATED)
    Podcast createPodcast(@RequestBody PodcastDto podcastDto);

    /**
     * Star Podcast
     * @param id ID of Podcast
     * @return Podcast
     */
    @PostMapping("/podcasts/{id}/star")
    @ResponseStatus(HttpStatus.OK)
    Podcast starPodcast(@PathVariable String id);

    /**
     * Comment Podcast
     * @param id ID of Podcast
     * @param commentId ID of Comment
     * @return Podcast
     */
    @PostMapping("/podcasts/{id}/comment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    Podcast commentPodcast(@PathVariable String id, @PathVariable Long commentId);

    /**
     * Uncomment Podcast
     * @param id ID of Podcast
     * @param commentId ID of Comment
     * @return Podcast
     */
    @PostMapping("/podcasts/{id}/uncomment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    Podcast uncommentPodcast(@PathVariable String id, @PathVariable Long commentId);

    /**
     * Update Podcast
     * @param id ID of Podcast
     * @param podcast Podcast updates
     * @return Podcast updated
     */
    @PatchMapping("/podcasts/{id}")
    @ResponseStatus(HttpStatus.OK)
    Podcast updatePodcast(@PathVariable String id, @RequestBody PodcastDto podcast);

    /**
     * Update Podcast audio
     * @param id ID of Podcast
     * @param file Audio update
     * @return Podcast
     */
    @PostMapping("/podcasts/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    Podcast updatePodcastAudio(@PathVariable String id, @RequestBody String file);

    /**
     * Remove Podcast
     * @param id ID of Podcast
     */
    @DeleteMapping("/podcasts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removePodcast(@PathVariable String id);

    //PLAYLIST

    /**
     * Find all Playlists
     * @return List of Playlists
     */
    @GetMapping("/playlists")
    @ResponseStatus(HttpStatus.OK)
    List<Playlist> findAllPlaylists();

    /**
     * Find Playlist by ID
     * @param id ID of Playlist
     * @return Playlist
     */
    @GetMapping("/playlists/{id}")
    @ResponseStatus(HttpStatus.OK)
    Playlist findPlaylistById(@PathVariable String id);

    /**
     * Create Playlist
     * @param playlist Playlist to Create
     * @return Playlist created
     */
    @PostMapping("/playlists")
    @ResponseStatus(HttpStatus.CREATED)
    Playlist createPlaylist(@RequestBody Playlist playlist);

    /**
     * Add Podcast to Playlist
     * @param id ID of Playlist
     * @param podcastId ID of Podcast
     * @return Playlist
     */
    @PostMapping("/playlists/{id}/add/{podcastId}")
    @ResponseStatus(HttpStatus.OK)
    Playlist addPodcastToPlaylist(@PathVariable String id, @PathVariable String podcastId);

    /**
     * Remove Podcast from Playlist
     * @param id ID of Playlist
     * @param podcastId ID of Podcast
     * @return Playlist
     */
    @PostMapping("/playlists/{id}/remove/{podcastId}")
    @ResponseStatus(HttpStatus.OK)
    Playlist removePodcastFromPlaylist(@PathVariable String id, @PathVariable String podcastId);

    /**
     * Update Playlist
     * @param id Id of Playlist
     * @param playlist Playlist to update
     * @return Playlist updated
     */
    @PatchMapping("/playlists/{id}")
    @ResponseStatus(HttpStatus.OK)
    Playlist updatePlaylist(@PathVariable String id, @RequestBody Playlist playlist);

    @DeleteMapping("/playlists/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removePlaylist(@PathVariable String id);
}
