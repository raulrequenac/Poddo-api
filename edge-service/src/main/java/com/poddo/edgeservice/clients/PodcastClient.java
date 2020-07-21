package com.poddo.edgeservice.clients;

import com.poddo.edgeservice.dto.PodcastDto;
import com.poddo.edgeservice.model.Playlist;
import com.poddo.edgeservice.model.Podcast;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "podcast-service", url = "http://localhost:8081")
public interface PodcastClient {
    //PODCAST
    @GetMapping("/podcasts")
    @ResponseStatus(HttpStatus.OK)
    List<Podcast> findAllPodcasts();

    @GetMapping("/podcasts/stars") //?title=bar
    @ResponseStatus(HttpStatus.OK)
    List<Podcast> findAllOrderByStarsDesc(@RequestParam(value = "title", required = false) String title);

    @GetMapping("/podcasts/{id}")
    @ResponseStatus(HttpStatus.OK)
    Podcast findPodcastById(@PathVariable String id);

    @PostMapping("/podcasts")
    @ResponseStatus(HttpStatus.CREATED)
    Podcast createPodcast(@RequestBody PodcastDto podcastDto);

    @PostMapping("/podcasts/{id}/star")
    @ResponseStatus(HttpStatus.OK)
    Podcast starPodcast(@PathVariable String id);

    @PostMapping("/podcasts/{id}/comment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    Podcast commentPodcast(@PathVariable String id, @PathVariable Long commentId);

    @PostMapping("/podcasts/{id}/uncomment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    Podcast uncommentPodcast(@PathVariable String id, @PathVariable Long commentId);

    @PatchMapping("/podcasts/{id}")
    @ResponseStatus(HttpStatus.OK)
    Podcast updatePodcast(@PathVariable String id, @RequestBody PodcastDto podcast);

    @PostMapping("/podcasts/{id}/update/{file}")
    @ResponseStatus(HttpStatus.OK)
    Podcast updatePodcastAudio(@PathVariable String id, @PathVariable String file);

    @DeleteMapping("/podcasts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removePodcast(@PathVariable String id);

    //PLAYLIST
    @GetMapping("/playlists")
    @ResponseStatus(HttpStatus.OK)
    List<Playlist> findAllPlaylists();

    @GetMapping("/playlists/{id}")
    @ResponseStatus(HttpStatus.OK)
    Playlist findPlaylistById(@PathVariable String id);

    @PostMapping("/playlists")
    @ResponseStatus(HttpStatus.CREATED)
    Playlist createPlaylist(@RequestBody Playlist playlist);

    @PostMapping("/playlists/{id}/add/{podcastId}")
    @ResponseStatus(HttpStatus.OK)
    Playlist addPodcastToPlaylist(@PathVariable String id, @PathVariable String podcastId);

    @PostMapping("/playlists/{id}/remove/{podcastId}")
    @ResponseStatus(HttpStatus.OK)
    Playlist removePodcastFromPlaylist(@PathVariable String id, @PathVariable String podcastId);

    @PatchMapping("/playlists/{id}")
    @ResponseStatus(HttpStatus.OK)
    Playlist updatePlaylist(@PathVariable String id, @RequestBody Playlist playlist);

    @DeleteMapping("/playlists/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removePlaylist(@PathVariable String id);
}
