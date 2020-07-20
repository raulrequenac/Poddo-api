package com.poddo.edgeservice.globalHandler;

import com.poddo.edgeservice.exceptions.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(ChannelServiceException.class)
    public void handleChannelServiceException(ChannelServiceException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_GONE, e.getMessage());
    }

    @ExceptionHandler(CommentServiceException.class)
    public void handleCommentServiceException(CommentServiceException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_GONE, e.getMessage());
    }

    @ExceptionHandler(PlaylistServiceException.class)
    public void handlePlaylistServiceException(PlaylistServiceException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_GONE, e.getMessage());
    }

    @ExceptionHandler(PodcastServiceException.class)
    public void handlePodcastServiceException(PodcastServiceException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_GONE, e.getMessage());
    }

    @ExceptionHandler(UserServiceException.class)
    public void handleUserServiceException(UserServiceException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_GONE, e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public void handleUsernameNotFoundException(UsernameNotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }
}
