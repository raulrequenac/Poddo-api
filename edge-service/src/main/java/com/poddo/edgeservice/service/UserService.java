package com.poddo.edgeservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.poddo.edgeservice.clients.UserClient;
import com.poddo.edgeservice.dto.ChannelUserDto;
import com.poddo.edgeservice.enums.Role;
import com.poddo.edgeservice.exceptions.UserServiceException;
import com.poddo.edgeservice.model.Channel;
import com.poddo.edgeservice.model.Playlist;
import com.poddo.edgeservice.model.User;
import com.poddo.edgeservice.security.CustomSecurityUser;
import com.poddo.edgeservice.viewModel.ChannelView;
import com.poddo.edgeservice.viewModel.PodcastView;
import com.poddo.edgeservice.viewModel.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService, Serializable {
    @Autowired
    private UserClient userClient;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private PodcastService podcastService;
    @Autowired
    private CommentService commentService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userClient.findByUsername(username);
        if (user==null) throw new UsernameNotFoundException("User not found");

        return new CustomSecurityUser(user);
    }

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public List<UserView> findAll(String username) {
        return convertListToView(userClient.findAll(username));
    }

    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public UserView findById(Long id) {
        return convertToView(userClient.findById(id));
    }

    @HystrixCommand(fallbackMethod = "findByUsernameFallback")
    public UserView findByUsername(String username) {
        return convertToView(userClient.findByUsername(username));
    }

    public UserView isUser(User user) {
        User u = userClient.findByUsername(user.getUsername());
        if (u==null || !u.getPassword().equals(user.getPassword())) return null;
        return convertToView(u);
    }

    @HystrixCommand(fallbackMethod = "createAdminFallback")
    public UserView createAdmin(User auth, User user) {
        if (!auth.getRole().equals(Role.ADMIN)) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        return convertToView(userClient.createAdmin(user));
    }

    @HystrixCommand(fallbackMethod = "createUserFallback")
    public UserView createUser(ChannelUserDto channelUserDto) {
        User user = userClient.createUser(new User(channelUserDto.getUsername(), channelUserDto.getPassword(), Role.USER));
        channelService.create(new Channel(user.getId(), channelUserDto.getName()));

        return convertToView(user);
    }

    @HystrixCommand(fallbackMethod = "subscribeFallback")
    public UserView subscribe(Long id, Long channelId) {
        return convertToView(userClient.subscribe(id, channelId));
    }

    @HystrixCommand(fallbackMethod = "unsubscribeFallback")
    public UserView unsubscribe(Long id, Long channelId) {
        return convertToView(userClient.unsubscribe(id, channelId));
    }

    @HystrixCommand(fallbackMethod = "updateFallback")
    public UserView update(User auth, Long id, User user) {
        UserView u = findByUsername(auth.getUsername());
        if (!u.getId().equals(id)) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        return convertToView(userClient.update(id, user));
    }

    @HystrixCommand(fallbackMethod = "removeFallback")
    public void remove(User auth, Long id) {
        UserView u = findByUsername(auth.getUsername());
        if (!auth.getRole().equals(Role.ADMIN) && !u.getId().equals(id))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        UserView user = findById(id);
        ChannelView channel = user.getChannel();
        List<Playlist> playlists = channel.getPlaylists();
        List<PodcastView> podcasts = channel.getPodcasts();

        podcasts.forEach(podcast -> podcast.getComments().forEach(comment -> commentService.remove(comment.getId())));
        podcasts.forEach(podcast -> podcastService.remove(auth, podcast.getId(), channel.getId()));
        playlists.forEach(playlist -> playlistService.remove(auth, playlist.getId()));
        channelService.remove(channel.getId());
        userClient.remove(id);
    }

    public UserView convertToView(User user) {
        List<ChannelView> channels = user.getSubscriptions()
                .stream()
                .map(channelId -> channelService.findById(channelId))
                .collect(Collectors.toList());
        Long userId = user.getId();
        ChannelView channel = channelService.findById(userId);

        return new UserView(userId, user.getUsername(), user.getRole(), channels, user.getCreatedAt(), channel);
    }

    public List<UserView> convertListToView(List<User> users) {
        return users.stream().map(this::convertToView).collect(Collectors.toList());
    }

    public List<UserView> findAllFallback(String username) {
        throw new UserServiceException("findAll");
    }

    public UserView findByIdFallback(Long id) {
        throw new UserServiceException("findById");
    }

    public UserView findByUsernameFallback(String username) {
        throw new UserServiceException("findByUsername");
    }

    public UserView createAdminFallback(User auth, User user) {
        throw new UserServiceException("createAdmin");
    }

    public UserView createUserFallback(ChannelUserDto channelUserDto) {
        throw new UserServiceException("createUser");
    }

    public UserView updateFallback(User auth, Long id, User user) {
        throw new UserServiceException("update");
    }

    public UserView subscribeFallback(Long id, Long channelId) {
        throw new UserServiceException("subscribe");
    }

    public UserView unsubscribeFallback(Long id, Long channelId) {
        throw new UserServiceException("unsubscribe");
    }

    public void removeFallback(User auth, Long id) {
        throw new UserServiceException("remove");
    }
}
