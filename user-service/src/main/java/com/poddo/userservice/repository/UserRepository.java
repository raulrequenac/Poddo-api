package com.poddo.userservice.repository;

import com.poddo.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository of User Service
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find Users by username like
     * @param username Username to be searched
     * @return List of Users
     */
    List<User> findByUsernameLike(String username);

    /**
     * Find Users by username
     * @param username Username of User
     * @return User with username provided
     */
    Optional<User> findByUsername(String username);
}
