package com.github.nicejing.security.security.support;

import com.alibaba.fastjson.JSONObject;
import com.github.nicejing.security.utils.ResultBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 需要登录时处理
 * @author Nathan
 */
@Component
public class AdminAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.getWriter().write(JSONObject.toJSONString(ResultBean.fail("请先登录再操作")));
    }

}
