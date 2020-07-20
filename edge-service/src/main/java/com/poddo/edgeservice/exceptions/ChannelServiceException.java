package com.poddo.edgeservice.exceptions;

public class ChannelServiceException extends RuntimeException {
    public ChannelServiceException(String message) {
        super("Channel Service is not responding on method: "+message);
    }
}
