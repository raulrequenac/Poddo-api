package com.poddo.podcastservice.controller.impl;

import com.poddo.podcastservice.controller.interfaces.IPodcastController;
import com.poddo.podcastservice.dto.PodcastDto;
import com.poddo.podcastservice.model.Podcast;
import com.poddo.podcastservice.service.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class PodcastController implements IPodcastController {
    @Autowired
    private PodcastService podcastService;

    @GetMapping("/podcasts")
    @ResponseStatus(HttpStatus.OK)
    public List<Podcast> findAll() {
        return podcastService.findAll();
    }

    @GetMapping("/podcasts/stars") //?title=bar
    @ResponseStatus(HttpStatus.OK)
    public List<Podcast> findAllOrderByStarsDesc(@RequestParam(value = "title", required = false) String title) {
        return podcastService.findAllOrderByStarsDesc(title);
    }

    @GetMapping("/podcasts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Podcast findById(@PathVariable String id) {
        return podcastService.findById(id);
    }

    @PostMapping(value = "/podcasts")
    @ResponseStatus(HttpStatus.CREATED)
    public Podcast create(@RequestBody PodcastDto podcastDto) {
        return podcastService.create(podcastDto);
    }

    @PostMapping("/podcasts/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public Podcast updatePodcastAudio(@PathVariable String id, @RequestBody String file) {
        return podcastService.updatePodcastAudio(id, file);
    }

    @PostMapping("/podcasts/{id}/star")
    @ResponseStatus(HttpStatus.OK)
    public Podcast star(@PathVariable String id) {
        return podcastService.star(id);
    }

    @PostMapping("/podcasts/{id}/comment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public Podcast comment(@PathVariable String id, @PathVariable Long commentId) {
        return podcastService.comment(id, commentId);
    }

    @PostMapping("/podcasts/{id}/uncomment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public Podcast uncomment(@PathVariable String id, @PathVariable Long commentId) {
        return podcastService.uncomment(id, commentId);
    }

    @PatchMapping("/podcasts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Podcast update(@PathVariable String id, @RequestBody PodcastDto podcast) {
        return podcastService.update(id, podcast);
    }

    @DeleteMapping("/podcasts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String id) {
        podcastService.remove(id);
    }
}
