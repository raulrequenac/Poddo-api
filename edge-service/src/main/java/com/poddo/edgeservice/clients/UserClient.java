package com.poddo.edgeservice.clients;

import com.poddo.edgeservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service", url = "http://localhost:8083")
public interface UserClient {
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    List<User> findAll(@RequestParam(value = "username", required = false) String username);

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    User findById(@PathVariable Long id);

    @GetMapping("/users/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    User findByUsername(@PathVariable String username);

    @PostMapping("/users/admin")
    @ResponseStatus(HttpStatus.CREATED)
    User createAdmin(@RequestBody User user);

    @PostMapping("/users/user")
    @ResponseStatus(HttpStatus.CREATED)
    User createUser(@RequestBody User user);

    @PostMapping("/users/{id}/subscribe/{channelId}")
    @ResponseStatus(HttpStatus.CREATED)
    User subscribe(@PathVariable Long id, @PathVariable Long channelId);

    @PostMapping("/users/{id}/unsubscribe/{channelId}")
    @ResponseStatus(HttpStatus.CREATED)
    User unsubscribe(@PathVariable Long id, @PathVariable Long channelId);

    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    User update(@PathVariable Long id, @RequestBody User user);

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void remove(@PathVariable Long id);
}
