package com.poddo.userservice.controller.interfaces;

import com.poddo.userservice.model.User;

import java.util.List;

public interface IUserController {
    /**
     * Find All Users
     * @param username Username to be searched
     * @return List of Users
     */
    List<User> findAll(String username);

    /**
     * Find User by ID
     * @param id ID of User
     * @return User with ID provided
     */
    User findById(Long id);

    /**
     * Find User by username
     * @param username Username of User
     * @return User with username provided
     */
    User findByUsername(String username);

    /**
     * Create User admin
     * @param admin User admin to create
     * @return User admin created
     */
    User createAdmin(User admin);

    /**
     * Create User user
     * @param user User user to create
     * @return User user created
     */
    User createUser(User user);

    /**
     * Subscribe to Channel
     * @param id ID of User
     * @param channelId ID of Channel to subscribe to
     * @return User with ID provided
     */
    User subscribe(Long id, Long channelId);

    /**
     * Unsubscribe from Channel
     * @param id ID of User
     * @param channelId ID of Channel to unsubscribe from
     * @return User with ID provided
     */
    User unsubscribe(Long id, Long channelId);

    /**
     * Update User
     * @param id ID of User to update
     * @param userUpdate User updates
     * @return User updated with ID provided
     */
    User update(Long id, User userUpdate);

    /**
     * Remove User
     * @param id ID of User to Remove
     */
    void remove(Long id);
}
