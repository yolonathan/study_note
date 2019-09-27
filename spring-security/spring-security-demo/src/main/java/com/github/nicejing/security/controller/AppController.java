package com.github.nicejing.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jing Zhi Bao
 */
@RestController
@RequestMapping("/admin/api/")
public class AppController {

    @GetMapping("hello")
    public String hello(){
        return "hello app";
    }
}
