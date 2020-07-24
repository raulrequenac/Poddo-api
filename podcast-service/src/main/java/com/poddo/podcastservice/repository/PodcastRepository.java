package com.poddo.podcastservice.repository;

import com.poddo.podcastservice.model.Podcast;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface PodcastRepository extends MongoRepository<Podcast, String> {
    @Query(sort = "{stars: -1}")
    List<Podcast> findByTitleContainingOrderByStarsDesc(String title);
    @Query(value = "{tags: { $all: [?0] }}", sort = "{stars: -1}")
    List<Podcast> findByTagContainingOrderByStarsDesc(String tag);
    @Query(value = "{}", sort = "{stars: -1}")
    List<Podcast> findAllOrderByStarsDesc();
}
