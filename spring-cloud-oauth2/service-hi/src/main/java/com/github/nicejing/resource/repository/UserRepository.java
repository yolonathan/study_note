package com.github.nicejing.resource.repository;


import com.github.nicejing.resource.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Nathan
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
