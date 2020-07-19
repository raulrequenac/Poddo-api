package com.poddo.podcastservice.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryConfiguration {
        @Value("${cloudinary.cloud_name}")
        private String cloudName;

        @Value("${cloudinary.api_key}")
        private String apiKey;

        @Value("${cloudinary.api_secret}")
        private String apiSecret;

        @Bean
        public Cloudinary cloudinaryConfig() {
            Cloudinary cloudinary = null;
            Map<String, String> config = new HashMap<>();
            config.put("cloud_name", cloudName);
            config.put("api_key", apiKey);
            config.put("api_secret", apiSecret);
            cloudinary = new Cloudinary(config);
            return cloudinary;
        }
}
