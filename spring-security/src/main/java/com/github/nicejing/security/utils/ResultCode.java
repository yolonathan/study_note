package com.github.nicejing.security.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Nathan
 */
@Getter
@AllArgsConstructor
public enum  ResultCode {

    /**
     * 默认成功的返回
     */
    SUCCESS(200, "success"),
    /**
     * 默认失败的返回
     */
    FAIL(-1, "fail");

    private int code;
    private String msg;


}
