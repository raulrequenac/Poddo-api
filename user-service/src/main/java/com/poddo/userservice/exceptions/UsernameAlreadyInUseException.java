package com.poddo.userservice.exceptions;

/**
 * Username already in use exception
 */
public class UsernameAlreadyInUseException extends RuntimeException {
    /**
     * Constructor
     * @param message Error message
     */
    public UsernameAlreadyInUseException(String message) {
        super(message);
    }
}
