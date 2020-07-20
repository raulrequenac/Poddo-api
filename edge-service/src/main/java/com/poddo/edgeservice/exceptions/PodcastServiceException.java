package com.poddo.edgeservice.exceptions;

public class PodcastServiceException extends RuntimeException {
    public PodcastServiceException(String message) {
        super("Podcast Service is not responding on method: "+message);
    }
}
