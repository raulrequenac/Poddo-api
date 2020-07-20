package com.poddo.cloudinaryservice.controller.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface ICloudinaryController {
    String uploadFile(MultipartFile file);
}
