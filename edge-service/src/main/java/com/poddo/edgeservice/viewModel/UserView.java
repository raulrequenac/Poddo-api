package com.poddo.edgeservice.viewModel;

import com.poddo.edgeservice.enums.Role;

import java.time.LocalDate;
import java.util.List;

public class UserView {
    private Long id;
    private String username;
    private Role role;
    private List<ChannelView> subscriptions;
    private LocalDate createdAt;
    private ChannelView channel;

    public UserView(Long id, String username, Role role, List<ChannelView> subscriptions, LocalDate createdAt, ChannelView channel) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.subscriptions = subscriptions;
        this.createdAt = createdAt;
        this.channel = channel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<ChannelView> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<ChannelView> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public ChannelView getChannel() {
        return channel;
    }

    public void setChannel(ChannelView channel) {
        this.channel = channel;
    }
}
