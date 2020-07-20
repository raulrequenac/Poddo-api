package com.poddo.cloudinaryservice.controller.impl;

import com.poddo.cloudinaryservice.controller.interfaces.ICloudinaryController;
import com.poddo.cloudinaryservice.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CloudinaryController implements ICloudinaryController {
    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public String uploadFile(@RequestBody MultipartFile file) {
        return cloudinaryService.uploadFile(file);
    }
}
