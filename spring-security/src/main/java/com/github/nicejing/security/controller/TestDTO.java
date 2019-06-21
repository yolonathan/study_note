package com.github.nicejing.security.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Nathan
 */
@Data
@AllArgsConstructor
public class TestDTO implements Serializable {

    private static final long serialVersionUID = 7872991675879830623L;
    private String            adminUsername;

    private String            fathanName;

    private LocalDateTime     localDateTime;
}
