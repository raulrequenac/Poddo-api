package com.poddo.userservice.controller.impl;

import com.poddo.userservice.model.User;
import com.poddo.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller of User Service
 */
@RestController
public class UserController {
    /**
     * User service
     */
    @Autowired
    private UserService userService;

    /**
     * Find all Users
     * @param username Username to be searched
     * @return List of Users
     */
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll(@RequestParam(value = "username", required = false) String username) {
        return userService.findAll(username);
    }

    /**
     * Find User by ID
     * @param id ID of User
     * @return User with ID provided
     */
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    /**
     * Find User by username
     * @param username Username of User
     * @return User with username provided
     */
    @GetMapping("/users/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    /**
     * Create User admin
     * @param user User admin to create
     * @return User admin createc
     */
    @PostMapping("/users/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public User createAdmin(@RequestBody User user) {
        return userService.createAdmin(user);
    }

    /**
     * Create User user
     * @param user User user to create
     * @return User user created
     */
    @PostMapping("/users/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * Subscribe to Channel
     * @param id ID of User
     * @param channelId ID of Channel to Subscribe to
     * @return User with ID provided
     */
    @PostMapping("/users/{id}/subscribe/{channelId}")
    @ResponseStatus(HttpStatus.OK)
    public User subscribe(@PathVariable Long id, @PathVariable Long channelId) {
        return userService.subscribe(id, channelId);
    }

    /**
     * Unsubscribe from Channel
     * @param id ID of User
     * @param channelId ID of Channel to unsubscribe from
     * @return User with ID provided
     */
    @PostMapping("/users/{id}/unsubscribe/{channelId}")
    @ResponseStatus(HttpStatus.OK)
    public User unsubscribe(@PathVariable Long id, @PathVariable Long channelId) {
        return userService.unsubscribe(id, channelId);
    }

    /**
     * Update User
     * @param id ID of User
     * @param user User updates
     * @return User updated
     */
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    /**
     * Remove User
     * @param id ID of User to remove
     */
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        userService.remove(id);
    }
}
