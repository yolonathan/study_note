package com.github.nicejing.security.auth.repository;

import com.github.nicejing.security.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Nathan
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
