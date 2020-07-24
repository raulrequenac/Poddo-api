package com.poddo.userservice.controller.impl;

import com.poddo.userservice.model.User;
import com.poddo.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll(@RequestParam(value = "username", required = false) String username) {
        return userService.findAll(username);
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/users/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/users/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public User createAdmin(@RequestBody User user) {
        return userService.createAdmin(user);
    }

    @PostMapping("/users/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/users/{id}/subscribe/{channelId}")
    @ResponseStatus(HttpStatus.OK)
    public User subscribe(@PathVariable Long id, @PathVariable Long channelId) {
        return userService.subscribe(id, channelId);
    }

    @PostMapping("/users/{id}/unsubscribe/{channelId}")
    @ResponseStatus(HttpStatus.OK)
    public User unsubscribe(@PathVariable Long id, @PathVariable Long channelId) {
        return userService.unsubscribe(id, channelId);
    }

    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        userService.remove(id);
    }
}
