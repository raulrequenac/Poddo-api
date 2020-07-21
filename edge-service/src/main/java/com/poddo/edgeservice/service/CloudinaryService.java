package com.poddo.edgeservice.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.poddo.edgeservice.exceptions.UploadException;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinaryConfig;

    public String uploadFile(MultipartFile file) {
        try {
            String base64 = Base64.toBase64String(file.getBytes());
            String convFile = "data:audio/mpeg;base64,"+base64;
            Map uploadResult = cloudinaryConfig.uploader()
                    .upload(convFile, ObjectUtils.asMap("resource_type", "auto"));
            return  uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new UploadException("The audio could not be uploaded.");
        }
    }
}
