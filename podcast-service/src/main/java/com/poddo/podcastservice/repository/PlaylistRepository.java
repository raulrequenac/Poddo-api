package com.poddo.podcastservice.repository;

import com.poddo.podcastservice.model.Playlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface PlaylistRepository extends MongoRepository<Playlist, Long> {
}
