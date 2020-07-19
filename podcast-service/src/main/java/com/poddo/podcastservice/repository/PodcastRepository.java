package com.poddo.podcastservice.repository;

import com.poddo.podcastservice.model.Podcast;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface PodcastRepository extends MongoRepository<Podcast, Long> {
    List<Podcast> findByTitleLikeOrderByStarsDesc(String title);
    List<Podcast> findAllOrderByStarsDesc();
}
