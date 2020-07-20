package com.poddo.podcastservice.repository;

import com.poddo.podcastservice.model.Podcast;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface PodcastRepository extends MongoRepository<Podcast, String> {
    @Query("SELECT p FROM Podcast p WHERE p.title LIKE CONCAT('%',:title,'%') ORDER BY p.stars DESC")
    List<Podcast> findByTitleLikeOrderByStarsDesc(String title);
    @Query("SELECT p FROM Podcast p ORDER BY p.stars DESC")
    List<Podcast> findAllOrderByStarsDesc();
}
