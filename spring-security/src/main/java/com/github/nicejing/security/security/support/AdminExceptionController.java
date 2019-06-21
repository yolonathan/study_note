package com.github.nicejing.security.security.support;

import com.github.nicejing.security.utils.ResultBean;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Nathan
 */
@RestController
public class AdminExceptionController implements ErrorController {

    @Override
    public String getErrorPath() {
        return null;
    }

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public ResultBean handleError(HttpServletRequest request) {
        // 获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == HttpStatus.BAD_REQUEST.value()) {
            return ResultBean.fail("Bad Request");
        } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
            return ResultBean.fail("Not Found");
        } else {
            return ResultBean.fail("Internal Server Error");
        }
    }
}
