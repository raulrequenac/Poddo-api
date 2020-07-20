package com.poddo.edgeservice.controller.interfaces;

import com.poddo.edgeservice.dto.PodcastDto;
import com.poddo.edgeservice.model.Podcast;

import java.util.List;

public interface IPodcastController {
    List<Podcast> findAll();
    List<Podcast> findAllOrderByStarsDesc(String title);
    Podcast findById(String id);
    Podcast create(PodcastDto podcastDto);
    Podcast star(String id);
    Podcast comment(String id, Long commentId);
    Podcast uncomment(String id, Long commentId);
    Podcast update(String id, Podcast podcast);
    void remove(String id);
}
