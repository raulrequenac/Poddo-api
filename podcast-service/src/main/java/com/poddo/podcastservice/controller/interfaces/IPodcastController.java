package com.poddo.podcastservice.controller.interfaces;

import com.poddo.podcastservice.model.Podcast;

import java.util.List;

public interface IPodcastController {
    List<Podcast> findAll();
    List<Podcast> findAllOrderByStarsDesc(String title, String tag);
    Podcast findById(String id);
    Podcast create(Podcast podcastDto);
    Podcast updatePodcastAudio(String id, String file);
    Podcast star(String id);
    Podcast comment(String id, Long commentId);
    Podcast uncomment(String id, Long commentId);
    Podcast update(String id, Podcast podcastUpdate);
    void remove(String id);
}
