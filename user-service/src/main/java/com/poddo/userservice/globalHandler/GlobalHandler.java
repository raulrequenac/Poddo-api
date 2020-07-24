package com.poddo.userservice.globalHandler;

import com.poddo.userservice.exceptions.IdNotFoundException;
import com.poddo.userservice.exceptions.UsernameAlreadyInUseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Global handler
 */
@ControllerAdvice
public class GlobalHandler {
    /**
     * Handler of ID not found exception
     * @param e ID not found exception
     * @param response HTTP Servlet Response
     * @throws IOException Input/Output Exception
     */
    @ExceptionHandler(IdNotFoundException.class)
    public void handleIdNotFoundException(IdNotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    /**
     * Handler of Username already in use exception
     * @param e Username already in use Exception
     * @param response HTTP Servlet Response
     * @throws IOException Input/Output exception
     */
    @ExceptionHandler(UsernameAlreadyInUseException.class)
    public void handleUsernameAlreadyInUseException(UsernameAlreadyInUseException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }
}
