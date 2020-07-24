package com.poddo.edgeservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.poddo.edgeservice.clients.ChannelClient;
import com.poddo.edgeservice.exceptions.ChannelServiceException;
import com.poddo.edgeservice.model.Channel;
import com.poddo.edgeservice.model.Playlist;
import com.poddo.edgeservice.model.Podcast;
import com.poddo.edgeservice.model.User;
import com.poddo.edgeservice.viewModel.ChannelView;
import com.poddo.edgeservice.viewModel.PodcastView;
import com.poddo.edgeservice.viewModel.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChannelService {
    @Autowired
    private ChannelClient channelClient;
    @Autowired
    private PodcastService podcastService;
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private UserService userService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public List<ChannelView> findAll(String name) {
        return channelClient.findAll(name).stream().map(this::convertToView).collect(Collectors.toList());
    }

    //@HystrixCommand(fallbackMethod = "findByIdFallback")
    public ChannelView findById(Long id) {
        return convertToView(channelClient.findById(id));
    }

    @HystrixCommand(fallbackMethod = "getPodcastsFallback")
    public List<PodcastView> getPodcasts(Long id) {
        return channelClient.getPodcasts(id)
                .stream()
                .map(podcastId -> podcastService.findById(podcastId))
                .collect(Collectors.toList());
    }

    @HystrixCommand(fallbackMethod = "getPlaylistsFallback")
    public List<Playlist> getPlaylists(Long id) {
        return channelClient.getPlaylists(id)
                .stream()
                .map(playListId -> playlistService.findById(playListId))
                .collect(Collectors.toList());
    }

    @HystrixCommand(fallbackMethod = "createFallback")
    public ChannelView create(Channel channel) {
        return convertToView(channelClient.create(channel));
    }

    @HystrixCommand(fallbackMethod = "blockFallback")
    public ChannelView block(Long id) {
        return convertToView(channelClient.block(id));
    }

    @HystrixCommand(fallbackMethod = "unblockFallback")
    public ChannelView unblock(Long id) {
        return convertToView(channelClient.unblock(id));
    }

    @HystrixCommand(fallbackMethod = "addPodcastFallback")
    public void addPodcast(Long id, String podcastId) {
        convertToView(channelClient.addPodcast(id, podcastId));
    }

    @HystrixCommand(fallbackMethod = "removePodcastFallback")
    public void removePodcast(Long id, String podcastId) {
        convertToView(channelClient.removePodcast(id, podcastId));
    }

    @HystrixCommand(fallbackMethod = "addPlaylistFallback")
    public void addPlaylist(Long id, String playlistId) {
        convertToView(channelClient.addPlaylist(id, playlistId));
    }

    @HystrixCommand(fallbackMethod = "removePlaylistFallback")
    public void removePlaylist(Long id, String playlistId) {
        convertToView(channelClient.removePlaylist(id, playlistId));
    }

    @HystrixCommand(fallbackMethod = "removeFallback")
    public void remove(Long id) {
        channelClient.remove(id);
    }

    public ChannelView subscribe(Long id, User user) {
        UserView u = userService.isUser(user);

        if (u==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (u.getId().equals(id)) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        if (isSubscribed(u, id)) throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);

        userService.subscribe(u.getId(), id);

        return convertToView(channelClient.addSubscriber(u.getId()));
    }

    public ChannelView unsubscribe(Long id, User user) {
        UserView u = userService.isUser(user);

        if (u==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (u.getId().equals(id)) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        if (!isSubscribed(u, id)) throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);

        userService.unsubscribe(u.getId(), id);

        return convertToView(channelClient.removeSubscriber(u.getId()));
    }

    public boolean isSubscribed(UserView user, Long channelId) {
        return user.getSubscriptions().stream().anyMatch(channel -> channel.getId().equals(channelId));
    }

    //@HystrixCommand(fallbackMethod = "updateLogoFallback")
    public ChannelView updateLogo(Long id, MultipartFile file) {
        String url = cloudinaryService.uploadFile(file, "image/jpeg");

        return convertToView(channelClient.updateLogo(id, url));
    }

    public ChannelView convertToView(Channel channel) {
        List<String> podcastList =  channel.getPodcasts();
        List<PodcastView> podcasts = podcastList.size()>0 ?
                podcastList.stream().map(podcastId -> podcastService.findById(podcastId)).collect(Collectors.toList()) :
                new ArrayList<>();

        List<String> playlistList =  channel.getPlaylists();
        List<Playlist> playlists = playlistList.size()>0 ?
                playlistList.stream().map(playlistId -> playlistService.findById(playlistId)).collect(Collectors.toList()) :
                new ArrayList<>();

        return new ChannelView(channel.getId(), channel.getName(), channel.getLogo(), channel.getStatus(), channel.getSubscribers(), podcasts, playlists);
    }

    public List<ChannelView> findAllFallback(String name) {
        throw new ChannelServiceException("findAll");
    }

    public ChannelView findByIdFallback(Long id) {
        throw new ChannelServiceException("findById");
    }

    public List<PodcastView> getPodcastsFallback(Long id) {
        throw new ChannelServiceException("getPodcasts");
    }

    public List<Playlist> getPlaylistsFallback(Long id) {
        throw new ChannelServiceException("getPlaylists");
    }

    public ChannelView createFallback(Channel channel) {
        throw new ChannelServiceException("create");
    }

    public ChannelView blockFallback(Long id) {
        throw new ChannelServiceException("block");
    }

    public ChannelView unblockFallback(Long id) {
        throw new ChannelServiceException("unblock");
    }

    public void addPodcastFallback(Long id, String podcastId) {
        throw new ChannelServiceException("addPodcast");
    }

    public void removePodcastFallback(Long id, String podcastId) {
        throw new ChannelServiceException("removePodcast");
    }

    public void addPlaylistFallback(Long id, String playlistId) {
        throw new ChannelServiceException("addPlaylist");
    }

    public void removePlaylistFallback(Long id, String playlistId) {
        throw new ChannelServiceException("removePlaylist");
    }

    public void removeFallback(Long id) {
        throw new ChannelServiceException("remove");
    }

    public ChannelView updateLogoFallback(Long id, MultipartFile file) {
        throw new ChannelServiceException("updateLogo");
    }
}
