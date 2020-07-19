package com.poddo.podcastservice.controller.interfaces;

import com.poddo.podcastservice.dto.PodcastDto;
import com.poddo.podcastservice.model.Podcast;

import java.util.List;

public interface IPodcastController {
    List<Podcast> findAll();
    List<Podcast> findAllOrderByStarsDesc(String title);
    Podcast findById(Long id);
    Podcast create(PodcastDto podcastDto);
    Podcast star(Long id);
    Podcast comment(Long id, Long commentId);
    Podcast uncomment(Long id, Long commentId);
    Podcast update(Long id, Podcast podcastUpdate);
    void remove(Long id);
}
