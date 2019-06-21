package com.github.nicejing.security.security.support;

import com.alibaba.fastjson.JSONObject;
import com.github.nicejing.security.utils.ResultBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理
 * @author Nathan
 */
@Component
public class AdminAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        // 获取获取信息
        String message = exception.getMessage();
        response.getWriter().write(JSONObject.toJSONString(ResultBean.fail(message)));
    }
}
