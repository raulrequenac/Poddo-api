package com.poddo.edgeservice.service;

import com.poddo.edgeservice.clients.UserClient;
import com.poddo.edgeservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserClient userClient;

    public List<User> findAll(String username) {
        return userClient.findAll(username);
    }

    public User findByUsername(String username) {
        return userClient.findByUsername(username);
    }

    public User createAdmin(User user) {
        return userClient.createAdmin(user);
    }

    public User createUser(User user) {
        return userClient.createUser(user);
    }

    public User update(Long id, User user) {
        return userClient.update(id, user);
    }

    public void remove(Long id) {
        userClient.remove(id);
    }
}
