package com.github.nicejing.security.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.nicejing.security.utils.ResultBean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Nathan
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping("/hello")
    public ResultBean test() {

        return ResultBean.success(new TestDTO("Bob", "Nathan", LocalDateTime.now()));
    }

}
