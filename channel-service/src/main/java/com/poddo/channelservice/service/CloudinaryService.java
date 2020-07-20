package com.poddo.channelservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.poddo.channelservice.clients.CloudinaryClient;
import com.poddo.channelservice.exceptions.UploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CloudinaryService {
    @Autowired
    private CloudinaryClient cloudinaryClient;

    @HystrixCommand(fallbackMethod = "uploadFileFailed")
    public String uploadFile(MultipartFile file) {
        return cloudinaryClient.uploadFile(file);
    }

    public String uploadFileFailed(MultipartFile file) {
        throw new UploadException("A problem occurred while uploading the file.");
    }
}
