package com.poddo.edgeservice.dto;

import com.poddo.edgeservice.enums.Role;
import org.springframework.web.multipart.MultipartFile;

public class ChannelUserDto {
    private String name;
    private MultipartFile logo;
    private String username;
    private String password;

    public ChannelUserDto(String name, MultipartFile logo, String username, String password) {
        this.name = name;
        this.logo = logo;
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
