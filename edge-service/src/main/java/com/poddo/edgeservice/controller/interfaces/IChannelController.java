package com.poddo.edgeservice.controller.interfaces;

import com.poddo.edgeservice.model.Playlist;
import com.poddo.edgeservice.viewModel.ChannelView;
import com.poddo.edgeservice.viewModel.PodcastView;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IChannelController {
    List<ChannelView> findAll(String name);
    ChannelView findById(Long id);
    List<PodcastView> getPodcasts(Long id);
    List<Playlist> getPlaylists(Long id);
    ChannelView block(Long id);
    ChannelView unblock(Long id);
    ChannelView updateLogo(Long id, MultipartFile file);
}
