package com.poddo.commentservice.exceptions;

/**
 * Responding a response exception
 */
public class RespondingAResponseException extends RuntimeException {
    /**
     * Constructor
     * @param message Error message
     */
    public RespondingAResponseException(String message) {
        super(message);
    }
}
