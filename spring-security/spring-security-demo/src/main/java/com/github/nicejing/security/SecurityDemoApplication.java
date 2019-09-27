package com.github.nicejing.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jing Zhi Bao
 */
@RestController
@SpringBootApplication
public class SecurityDemoApplication {

    @GetMapping("/")
    public String hello(){
        return "Hello , Spring security";
    }

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApplication.class);
    }
}
