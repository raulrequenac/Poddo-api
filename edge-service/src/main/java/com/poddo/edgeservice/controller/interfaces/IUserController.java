package com.poddo.edgeservice.controller.interfaces;

import com.poddo.edgeservice.model.User;

import java.util.List;

public interface IUserController {
    List<User> findAll(String username);
    User findByUsername(String username);
    User createAdmin(User user);
    User createUser(User user);
    User update(Long id, User user);
    void remove(Long id);
}
