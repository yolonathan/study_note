package com.github.nicejing.security.security.support;


import com.github.nicejing.security.security.dto.AdminInfoDTO;
import com.github.nicejing.security.security.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Nathan
 */
@Component
public class AdminAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AdminSecurityProperties adminSecurityProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 设置响应编码
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding("UTF-8");
        String token = request.getHeader(adminSecurityProperties.getTokenName());
        if (token != null) {
            AdminInfoDTO infoDTO = TokenUtil.getUserInfoFormToken(token);
            if (infoDTO != null) {
                // 刷新token有效期
                TokenUtil.refreshToken(infoDTO.getId());
                List<GrantedAuthority> authorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList(StringUtils.join(infoDTO.getRoles(), ","));
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(infoDTO, null,
                        authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);

    }
}
