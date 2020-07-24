package com.poddo.commentservice.globalHandler;

import com.poddo.commentservice.exceptions.IdNotFoundException;
import com.poddo.commentservice.exceptions.RespondingAResponseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Global Handler
 */
@ControllerAdvice
public class GlobalHandler {
    /**
     * Handler of ID not found exception
     * @param e ID not found exception
     * @param response HTTP Servlet Response
     * @throws IOException Input/Output exception
     */
    @ExceptionHandler(IdNotFoundException.class)
    public void handleIdNotFoundException(IdNotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    /**
     * Handler of Responding a response exception
     * @param e Responding a response exception
     * @param response HTTP Servlet Response
     * @throws IOException Input/Output exception
     */
    @ExceptionHandler(RespondingAResponseException.class)
    public void handleRespondingAResponseException(RespondingAResponseException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }
}
