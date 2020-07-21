package com.poddo.edgeservice.controller.interfaces;

import com.poddo.edgeservice.dto.PodcastDto;
import com.poddo.edgeservice.model.Comment;
import com.poddo.edgeservice.model.Podcast;
import com.poddo.edgeservice.model.User;
import com.poddo.edgeservice.viewModel.PodcastView;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPodcastController {
    List<PodcastView> findAll();
    List<PodcastView> findAllOrderByStarsDesc(String title);
    PodcastView findById(String id);
    PodcastView create(User auth, PodcastDto podcastDto, String playlistId, Long channelId);
    PodcastView star(String id);
    PodcastView comment(String id, Long commentId, Comment comment);
    PodcastView uncomment(String id, Long commentId);
    PodcastView update(User auth, String id, PodcastDto podcast, Long channelId);
    void remove(User auth, String id, Long channelId);
    PodcastView uploadFile(String id, MultipartFile file);
}
