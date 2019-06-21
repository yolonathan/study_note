package com.github.nicejing.security.utils;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nathan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = 3245076943482045641L;

    private boolean           success;

    private int               code;

    private String            msg;

    private T                 data;

    public static <T> ResultBean<T> success() {
        return new ResultBean<>(true, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), null);
    }

    public static <T> ResultBean<T> success(int code, String msg) {
        return new ResultBean<>(true, code, msg, null);
    }

    public static <T> ResultBean<T> success(int code, String msg, T data) {
        return new ResultBean<>(true, code, msg, data);
    }

    public static <T> ResultBean<T> success(T data) {
        return new ResultBean<>(true, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    public static <T> ResultBean<T> success(String msg) {
        return new ResultBean<>(true, ResultCode.SUCCESS.getCode(), msg, null);
    }

    public static <T> ResultBean<T> fail() {
        return new ResultBean<>(false, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMsg(), null);
    }

    public static <T> ResultBean<T> fail(int code, String msg) {
        return new ResultBean<>(false, code, msg, null);
    }

    public static <T> ResultBean<T> fail(int code, String msg, T data) {
        return new ResultBean<>(false, code, msg, data);
    }

    public static <T> ResultBean<T> fail(T data) {
        return new ResultBean<>(false, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMsg(), data);
    }

    public static <T> ResultBean<T> fail(String msg) {
        return new ResultBean<>(false, ResultCode.FAIL.getCode(), msg, null);
    }

}
