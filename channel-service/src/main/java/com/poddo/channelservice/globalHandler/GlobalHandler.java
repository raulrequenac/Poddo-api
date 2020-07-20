package com.poddo.channelservice.globalHandler;

import com.poddo.channelservice.exceptions.IdNotFoundException;
import com.poddo.channelservice.exceptions.UploadException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(IdNotFoundException.class)
    public void handleIdNotFoundException(IdNotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(UploadException.class)
    public void handleUploadException(UploadException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }
}
