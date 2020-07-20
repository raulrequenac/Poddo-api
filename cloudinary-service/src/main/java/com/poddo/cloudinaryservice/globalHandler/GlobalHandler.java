package com.poddo.cloudinaryservice.globalHandler;

import com.poddo.cloudinaryservice.exceptions.UploadException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(UploadException.class)
    public void handleUploadException(UploadException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }
}
