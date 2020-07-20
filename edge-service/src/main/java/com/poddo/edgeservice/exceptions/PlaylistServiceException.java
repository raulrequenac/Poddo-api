package com.poddo.edgeservice.exceptions;

public class PlaylistServiceException extends RuntimeException {
    public PlaylistServiceException(String message) {
        super("Playlist Service is not responding on method: "+message);
    }
}
