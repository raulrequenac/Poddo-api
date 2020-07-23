package com.poddo.edgeservice.controller.impl;

import com.poddo.edgeservice.controller.interfaces.IUserController;
import com.poddo.edgeservice.dto.ChannelUserDto;
import com.poddo.edgeservice.model.User;
import com.poddo.edgeservice.service.UserService;
import com.poddo.edgeservice.viewModel.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController implements IUserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserView> findAll(@RequestParam(value = "username", required = false) String username) {
        return userService.findAll(username);
    }

    @GetMapping("/users/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserView findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/users/is-user")
    @ResponseStatus(HttpStatus.OK)
    public UserView isUser(@AuthenticationPrincipal User user) {
        return userService.isUser(user);
    }

    @PostMapping("/users/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public UserView createAdmin(@AuthenticationPrincipal User auth, @RequestBody User user) {
        return userService.createAdmin(auth, user);
    }

    @PostMapping("/users/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserView createUser(@RequestBody ChannelUserDto channelUserDto) {
        return userService.createUser(channelUserDto);
    }

    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserView update(@AuthenticationPrincipal User auth, @PathVariable Long id, @RequestBody User user) {
        return userService.update(auth, id, user);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@AuthenticationPrincipal User auth, @PathVariable Long id) {
        userService.remove(auth, id);
    }
}
