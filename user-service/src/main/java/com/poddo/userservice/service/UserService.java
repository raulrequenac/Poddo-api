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
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(String username) {
        return username==null ? userRepository.findAll() : userRepository.findByUsernameLike(username);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Username with id "+id+" not found."));
    }

    public User createAdmin(User admin) {
        if (findByUsername(admin.getUsername())!=null)
            throw new UsernameAlreadyInUseException("The username "+admin.getUsername()+" is already in use.");

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole(Role.ADMIN);

        return userRepository.save(admin);
    }

    public User createUser(User user) {
        if (findByUsername(user.getUsername())!=null)
            throw new UsernameAlreadyInUseException("The username "+user.getUsername()+" is already in use.");

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(user.getPassword());

        return userRepository.save(new User(user.getUsername(), password, Role.USER));
    }

    public User update(Long id, User userUpdate) {
        User user = findById(id);

        if (userUpdate.getUsername()!=null) user.setUsername(userUpdate.getUsername());
        if (userUpdate.getPassword()!=null) user.setPassword(userUpdate.getPassword());

        return userRepository.save(user);
    }

    public void remove(Long id) {
        userRepository.deleteById(id);
    }
}
