package com.poddo.userservice.exceptions;

/**
 * ID not found exception
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
