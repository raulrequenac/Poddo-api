package com.poddo.userservice.controller.interfaces;

import com.poddo.userservice.model.User;

import java.util.List;

public interface IUserController {
    List<User> findAll(String username);
    User findById(Long id);
    User findByUsername(String username);
    User createAdmin(User admin);
    User createUser(User user);
    User update(Long id, User userUpdate);
    void remove(Long id);
}
