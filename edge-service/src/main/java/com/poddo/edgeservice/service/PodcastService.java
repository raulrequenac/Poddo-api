package com.poddo.edgeservice.service;

import com.poddo.edgeservice.clients.PodcastClient;
import com.poddo.edgeservice.dto.PodcastDto;
import com.poddo.edgeservice.model.Podcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PodcastService {
    @Autowired
    private PodcastClient podcastClient;

    public List<Podcast> findAll() {
        return podcastClient.findAllPodcasts();
    }

    public List<Podcast> findAllOrderByStarsDesc(String title) {
        return podcastClient.findAllOrderByStarsDesc(title);
    }

    public Podcast findById(String id) {
        return podcastClient.findPodcastById(id);
    }

    public Podcast create(PodcastDto podcastDto) {
        return podcastClient.createPodcast(podcastDto);
    }

    public Podcast star(String id) {
        return podcastClient.starPodcast(id);
    }

    public Podcast comment(String id, Long commentId) {
        return podcastClient.commentPodcast(id, commentId);
    }

    public Podcast uncomment(String id, Long commentId) {
        return podcastClient.uncommentPodcast(id, commentId);
    }

    public Podcast update(String id, Podcast podcast) {
        return podcastClient.updatePodcast(id, podcast);
    }

    public void remove(String id) {
        podcastClient.removePodcast(id);
    }
}
