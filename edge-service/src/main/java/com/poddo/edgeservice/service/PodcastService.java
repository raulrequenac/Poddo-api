package com.poddo.edgeservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.poddo.edgeservice.clients.PodcastClient;
import com.poddo.edgeservice.dto.PodcastDto;
import com.poddo.edgeservice.enums.Role;
import com.poddo.edgeservice.exceptions.PodcastServiceException;
import com.poddo.edgeservice.model.Comment;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PodcastService {
    @Autowired
    private PodcastClient podcastClient;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private UserService userService;
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public List<PodcastView> findAll() {
        List<Podcast> podcasts = podcastClient.findAllPodcasts();

        return convertListToView(podcasts);
    }

    @HystrixCommand(fallbackMethod = "findAllOrderByStarsDescFallback")
    public List<PodcastView> findAllOrderByStarsDesc(String title) {
        List<Podcast> podcasts = podcastClient.findAllOrderByStarsDesc(title);

        return convertListToView(podcasts);
    }

    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public PodcastView findById(String id) {
        return convertToView(podcastClient.findPodcastById(id));
    }

    @HystrixCommand(fallbackMethod = "createFallback")
    public PodcastView create(User auth, PodcastDto podcastDto, String playlistId, Long channelId) {
        UserView user = userService.findByUsername(auth.getUsername());
        if (!user.getChannel().getId().equals(channelId)) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        PodcastView podcast = convertToView(podcastClient.createPodcast(podcastDto));
        channelService.addPodcast(channelId, podcast.getId());

        if (playlistId!=null) playlistService.addPodcast(auth, playlistId, podcast.getId(), channelId);

        return podcast;
    }

    @HystrixCommand(fallbackMethod = "starFallback")
    public PodcastView star(String id) {
        return convertToView(podcastClient.starPodcast(id));
    }

    @HystrixCommand(fallbackMethod = "commentFallback")
    public PodcastView comment(String id, Long commentId, Comment comment) {
        commentService.create(comment);
        return convertToView(podcastClient.commentPodcast(id, commentId));
    }

    @HystrixCommand(fallbackMethod = "uncommentFallback")
    public PodcastView uncomment(String id, Long commentId) {
        commentService.remove(commentId);
        return convertToView(podcastClient.uncommentPodcast(id, commentId));
    }

    @HystrixCommand(fallbackMethod = "updateFallback")
    public PodcastView update(User auth, String id, PodcastDto podcast, Long channelId) {
        UserView u = userService.findByUsername(auth.getUsername());
        ChannelView channel = channelService.findById(channelId);

        if (!u.getChannel().getId().equals(channelId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        if (!channel.getPodcasts().contains(findById(id)))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        return convertToView(podcastClient.updatePodcast(id, podcast));
    }

    @HystrixCommand(fallbackMethod = "removeFallback")
    public void remove(User auth, String id, Long channelId) {
        UserView u = userService.findByUsername(auth.getUsername());
        ChannelView channel = channelService.findById(channelId);

        if (!auth.getRole().equals(Role.ADMIN) && !u.getChannel().getId().equals(channelId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        if (!channel.getPodcasts().contains(findById(id)))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        channel.getPlaylists().forEach(playlist -> playlist.getPodcasts().forEach(podcast -> {
            if (podcast.getId().equals(id)) playlistService.removePodcast(auth, playlist.getId(), podcast.getId(), channelId);
        }));
        channelService.removePodcast(channel.getId(), id);
        podcastClient.removePodcast(id);
    }

    @HystrixCommand(fallbackMethod = "uploadFileFallback")
    public PodcastView uploadFile(String id, MultipartFile file) {
        String url = cloudinaryService.uploadFile(file);

        return convertToView(podcastClient.updatePodcastAudio(id, url));
    }

    public PodcastView convertToView(Podcast podcast) {
        List<Comment> comments = podcast.getComments()
                .stream()
                .map(commentId -> commentService.findById(commentId))
                .collect(Collectors.toList());
        return new PodcastView(
                podcast.getId(),
                podcast.getStars(),
                podcast.getTags(),
                podcast.getTitle(),
                podcast.getDescription(),
                comments,
                podcast.getStatus(),
                podcast.getAudio(),
                podcast.getAllowComments(),
                podcast.getCreationDate()
        );
    }

    public List<PodcastView> convertListToView(List<Podcast> podcasts) {
        return podcasts.stream().map(this::convertToView).collect(Collectors.toList());
    }

    public List<PodcastView> findAllFallback() {
        throw new PodcastServiceException("findAll");
    }

    public List<PodcastView> findAllOrderByStarsDescFallback(String title) {
        throw new PodcastServiceException("findAllOrderByStarsDesc");
    }

    public PodcastView findByIdFallback(String id) {
        throw new PodcastServiceException("findById");
    }

    public PodcastView createFallback(User auth, PodcastDto podcastDto, String playlistId, Long channelId) {
        throw new PodcastServiceException("create");
    }

    public PodcastView starFallback(String id) {
        throw new PodcastServiceException("star");
    }

    public PodcastView commentFallback(String id, Long commentId, Comment comment) {
        throw new PodcastServiceException("comment");
    }

    public PodcastView uncommentFallback(String id, Long commentId) {
        throw new PodcastServiceException("uncomment");
    }

    public PodcastView updateFallback(User auth, String id, PodcastDto podcast, Long channelId) {
        throw new PodcastServiceException("update");
    }

    public void removeFallback(User auth, String id, Long channelId) {
        throw new PodcastServiceException("remove");
    }

    public PodcastView uploadFileFallback(String id, MultipartFile file) {
        throw new PodcastServiceException("uploadFile");
    }
}
