package com.poddo.edgeservice.model;

import com.poddo.edgeservice.enums.Role;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private Role role;
    private List<Long> subscriptions;
    private LocalDate createdAt;

    public User() {
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.subscriptions = new ArrayList<>();
        this.createdAt = LocalDate.now();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Long> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Long> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
