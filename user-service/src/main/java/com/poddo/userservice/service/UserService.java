package com.poddo.userservice.service;

import com.poddo.userservice.enums.Role;
import com.poddo.userservice.exceptions.IdNotFoundException;
import com.poddo.userservice.exceptions.UsernameAlreadyInUseException;
import com.poddo.userservice.model.User;
import com.poddo.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service of User Service
 */
@Service
public class UserService {
    /**
     * User repository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Find all Users
     * @param username Username to be searched
     * @return List of Users
     */
    public List<User> findAll(String username) {
        return username==null ? userRepository.findAll() : userRepository.findByUsernameLike(username);
    }

    /**
     * Find User by username
     * @param username Username to be searched
     * @return User with username provided
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    /**
     * Find User by ID
     * @param id ID of User
     * @return User with ID provided
     */
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Username with id "+id+" not found."));
    }

    /**
     * Create User admin
     * @param admin User admin to create
     * @return User admin created
     */
    public User createAdmin(User admin) {
        if (findByUsername(admin.getUsername())!=null)
            throw new UsernameAlreadyInUseException("The username "+admin.getUsername()+" is already in use.");

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole(Role.ADMIN);

        return userRepository.save(admin);
    }

    /**
     * Create User user
     * @param user User user to create
     * @return User user created
     */
    public User createUser(User user) {
        if (findByUsername(user.getUsername())!=null)
            throw new UsernameAlreadyInUseException("The username "+user.getUsername()+" is already in use.");

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(user.getPassword());

        return userRepository.save(new User(user.getUsername(), password, Role.USER));
    }

    /**
     * Update User
     * @param id ID of User to update
     * @param userUpdate User updates
     * @return User updated
     */
    public User update(Long id, User userUpdate) {
        User user = findById(id);

        if (userUpdate.getUsername()!=null) user.setUsername(userUpdate.getUsername());
        if (userUpdate.getPassword()!=null) user.setPassword(userUpdate.getPassword());

        return userRepository.save(user);
    }

    /**
     * Remove User
     * @param id ID of User to remove
     */
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Subscribe to Channel
     * @param id ID of User
     * @param channelId ID of Channel to Subscribe to
     * @return User with ID provided
     */
    public User subscribe(Long id, Long channelId) {
        User user = findById(id);
        user.subscribe(channelId);

        return userRepository.save(user);
    }

    /**
     * Unsubscribe from Channel
     * @param id ID of User
     * @param channelId ID of Channel to unsubscribe from
     * @return User with ID provided
     */
    public User unsubscribe(Long id, Long channelId) {
        User user = findById(id);
        user.unsubscribe(channelId);

        return userRepository.save(user);
    }
}
