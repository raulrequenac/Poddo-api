package com.poddo.userservice.repository;

import com.poddo.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameLike(String username);
    Optional<User> findByUsername(String username);
}
