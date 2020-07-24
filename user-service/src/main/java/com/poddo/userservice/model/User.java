package com.poddo.userservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.poddo.userservice.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * User model
 */
@Entity
public class User {
    /**
     * ID of User
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Username of User
     */
    @NotNull
    private String username;
    /**
     * Password of User
     */
    @NotNull
    private String password;
    /**
     * Role of User
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
    /**
     * Subscriptions of User
     */
    @ElementCollection
    private List<Long> subscriptions;
    /**
     * Date of creation of User
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate createdAt;

    /**
     * Empty Constructor
     */
    public User() {
    }

    /**
     * Constructor
     * @param username Username of User
     * @param password Password of User
     * @param role Role of User
     */
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.subscriptions = new ArrayList<>();
        this.createdAt = LocalDate.now();
    }

    /**
     * Get ID of User
     * @return ID of User
     */
    public Long getId() {
        return id;
    }

    /**
     * Set ID of User
     * @param id ID of User
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get Username of User
     * @return Username of User
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set Username of User
     * @param username Username of USer
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get Password of User
     * @return Password of User
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Password of User
     * @param password Password of User
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get Role of User
     * @return Role of User
     */
    public Role getRole() {
        return role;
    }

    /**
     * Set Role of User
     * @param role Role of User
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Get Subscriptions of User
     * @return Subscriptions of User
     */
    public List<Long> getSubscriptions() {
        return subscriptions;
    }

    /**
     * Subscribe to Channel
     * @param subscription ID of Channel to Subscribe
     */
    public void subscribe(Long subscription) {
        this.subscriptions.add(subscription);
    }

    /**
     * Unsubscribe from Channel
     * @param subscription ID of Channel to Unsubscribe
     */
    public void unsubscribe(Long subscription) {
        this.subscriptions.remove(subscription);
    }

    /**
     * Get Date of creation of User
     * @return Date of creation of User
     */
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    /**
     * Set Date of creation of User
     * @param createdAt Date of Creation of User
     */
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
