package com.poddo.commentservice.exceptions;

/**
 * ID not found Exception
 */
public class IdNotFoundException extends RuntimeException {
    /**
     * Constructor
     * @param message Error message
     */
    public IdNotFoundException(String message) {
        super(message);
    }
}
