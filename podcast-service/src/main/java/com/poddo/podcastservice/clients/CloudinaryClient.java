package com.poddo.podcastservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "cloudinary-service", url = "https://cloudinary-service-poddo.herokuapp.com")
public interface CloudinaryClient {
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public String uploadFile(@RequestBody MultipartFile file);
}
