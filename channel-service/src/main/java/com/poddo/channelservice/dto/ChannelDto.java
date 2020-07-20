package com.poddo.channelservice.dto;

import org.springframework.web.multipart.MultipartFile;

public class ChannelDto {
    private String name;
    private MultipartFile logo;
    private Long userId;

    public ChannelDto(String name, MultipartFile logo, Long userId) {
        this.name = name;
        this.logo = logo;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getLogo() {
        return logo;
    }

    public void setLogo(MultipartFile logo) {
        this.logo = logo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
