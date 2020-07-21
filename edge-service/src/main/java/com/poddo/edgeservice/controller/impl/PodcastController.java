package com.poddo.edgeservice.controller.impl;

import com.poddo.edgeservice.controller.interfaces.IPodcastController;
import com.poddo.edgeservice.dto.PodcastDto;
import com.poddo.edgeservice.model.Comment;
import com.poddo.edgeservice.model.Podcast;
import com.poddo.edgeservice.model.User;
import com.poddo.edgeservice.service.PodcastService;
import com.poddo.edgeservice.viewModel.PodcastView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class PodcastController implements IPodcastController {
    @Autowired
    private PodcastService podcastService;

    @GetMapping("/podcasts")
    @ResponseStatus(HttpStatus.OK)
    public List<PodcastView> findAll() {
        return podcastService.findAll();
    }

    @GetMapping("/podcasts/stars") //?title=bar
    @ResponseStatus(HttpStatus.OK)
    public List<PodcastView> findAllOrderByStarsDesc(@RequestParam(value = "title", required = false) String title) {
        return podcastService.findAllOrderByStarsDesc(title);
    }

    @GetMapping("/podcasts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PodcastView findById(@PathVariable String id) {
        return podcastService.findById(id);
    }

    @PostMapping("/podcasts/{channelId}")
    @ResponseStatus(HttpStatus.CREATED)
    public PodcastView create(@AuthenticationPrincipal User auth,
                              @RequestBody PodcastDto podcastDto,
                              @RequestParam(value = "playlistId", required = false) String playlistId,
                              @PathVariable Long channelId) {
        return podcastService.create(auth, podcastDto, playlistId, channelId);
    }

    @PostMapping("/podcasts/{id}/star")
    @ResponseStatus(HttpStatus.OK)
    public PodcastView star(@PathVariable String id) {
        return podcastService.star(id);
    }

    @PostMapping("/podcasts/{id}/comment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public PodcastView comment(@PathVariable String id, @PathVariable Long commentId, @RequestBody Comment comment) {
        return podcastService.comment(id, commentId, comment);
    }

    @PostMapping("/podcasts/{id}/uncomment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public PodcastView uncomment(@PathVariable String id, @PathVariable Long commentId) {
        return podcastService.uncomment(id, commentId);
    }

    @PatchMapping("/podcasts/{id}/to/{channelId}")
    @ResponseStatus(HttpStatus.OK)
    public PodcastView update(@AuthenticationPrincipal User auth,
                              @PathVariable String id,
                              @RequestBody PodcastDto podcast,
                              @PathVariable Long channelId) {
        return podcastService.update(auth, id, podcast, channelId);
    }

    @DeleteMapping("/podcasts/{id}/to/{channelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@AuthenticationPrincipal User auth, @PathVariable String id, @PathVariable Long channelId) {
        podcastService.remove(auth, id, channelId);
    }

    @PostMapping(value = "/podcasts/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PodcastView uploadFile(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        return podcastService.uploadFile(id, file);
    }
}
