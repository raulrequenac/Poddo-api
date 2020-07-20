package com.poddo.edgeservice.exceptions;

public class CommentServiceException extends RuntimeException {
    public CommentServiceException(String message) {
        super("Comment Service is not responding on method: "+message);
    }
}
