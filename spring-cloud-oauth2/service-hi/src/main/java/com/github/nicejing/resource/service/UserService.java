package com.github.nicejing.resource.service;

import com.github.nicejing.resource.entity.User;
import com.github.nicejing.resource.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Nathan
 */
@Service
public class UserService {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    public User create(String username, String password) {
        User user = new User();
        user.setUsername(username);
        String hash = encoder.encode(password);
        user.setPassword(hash);
        return userRepository.save(user);
    }
}
