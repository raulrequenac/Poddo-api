package com.poddo.channelservice.dto;

import org.springframework.web.multipart.MultipartFile;

public class ChannelDto {
    private Long id;
    private String name;
    private MultipartFile logo;

    public ChannelDto(Long id, String name, MultipartFile logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
