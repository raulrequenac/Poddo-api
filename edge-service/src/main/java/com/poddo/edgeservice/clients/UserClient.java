package com.poddo.edgeservice.clients;

import com.poddo.edgeservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User Client
 */
@FeignClient(name = "user-service", url = "http://localhost:8083")
public interface UserClient {
    /**
     * Find all Users
     * @param username Username to search
     * @return List of Users
     */
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    List<User> findAll(@RequestParam(value = "username", required = false) String username);

    /**
     * Find User by ID
     * @param id ID of User
     * @return User
     */
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    User findById(@PathVariable Long id);

    /**
     * Find User by username
     * @param username Username
     * @return User
     */
    @GetMapping("/users/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    User findByUsername(@PathVariable String username);

    /**
     * Create User admin
     * @param user User admin to create
     * @return User admin created
     */
    @PostMapping("/users/admin")
    @ResponseStatus(HttpStatus.CREATED)
    User createAdmin(@RequestBody User user);

    /**
     * Create User user
     * @param user User user to create
     * @return User user created
     */
    @PostMapping("/users/user")
    @ResponseStatus(HttpStatus.CREATED)
    User createUser(@RequestBody User user);

    /**
     * Subscribe to Channel
     * @param id ID of User
     * @param channelId ID of Channel
     * @return User
     */
    @PostMapping("/users/{id}/subscribe/{channelId}")
    @ResponseStatus(HttpStatus.CREATED)
    User subscribe(@PathVariable Long id, @PathVariable Long channelId);

    /**
     * Unsubscribe from Channel
     * @param id ID of User
     * @param channelId ID of Channel
     * @return User
     */
    @PostMapping("/users/{id}/unsubscribe/{channelId}")
    @ResponseStatus(HttpStatus.CREATED)
    User unsubscribe(@PathVariable Long id, @PathVariable Long channelId);

    /**
     * Update User
     * @param id ID of User
     * @param user User updates
     * @return User updated
     */
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    User update(@PathVariable Long id, @RequestBody User user);

    /**
     * Remove User
     * @param id ID of User
     */
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void remove(@PathVariable Long id);
}
