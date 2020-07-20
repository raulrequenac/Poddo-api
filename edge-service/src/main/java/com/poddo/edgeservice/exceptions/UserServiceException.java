package com.poddo.edgeservice.exceptions;

public class UserServiceException extends RuntimeException {
    public UserServiceException(String message) {
        super("User Service is not responding on method: "+message);
    }
}
