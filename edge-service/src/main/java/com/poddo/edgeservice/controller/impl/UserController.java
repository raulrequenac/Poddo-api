package com.poddo.edgeservice.controller.impl;

import com.poddo.edgeservice.controller.interfaces.IUserController;
import com.poddo.edgeservice.model.User;
import com.poddo.edgeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController implements IUserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll(@RequestParam(value = "username", required = false) String username) {
        return userService.findAll(username);
    }

    @GetMapping("/users/{username}")
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

    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        userService.remove(id);
    }
}
