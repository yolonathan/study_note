package com.github.nicejing.security.security.support;

import com.alibaba.fastjson.JSONObject;

import com.github.nicejing.security.security.dto.AdminInfoDTO;
import com.github.nicejing.security.security.utils.TokenUtil;
import com.github.nicejing.security.utils.ResultBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出处理
 * @author Nathan
 */
@Component
public class AdminLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        // 设置响应编码
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding("UTF-8");
        try {
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof AdminInfoDTO) {
                    AdminInfoDTO info = (AdminInfoDTO) principal;
                    TokenUtil.clean(info.getId());
                }
            }
            response.getWriter().write(JSONObject.toJSONString(ResultBean.success()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
