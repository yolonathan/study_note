package com.github.nicejing.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Nathan
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.github.nicejing")
@EnableTransactionManagement
public class SecurityApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SecurityApplication.class);
    }
}
