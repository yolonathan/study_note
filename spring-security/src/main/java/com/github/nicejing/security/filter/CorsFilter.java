package com.github.nicejing.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nathan
 */
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class CorsFilter implements Filter {

    private static final String OPTIONS_METHOD_NAME = "OPTIONS";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
                                                                                     ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String origin = request.getHeader("Origin");
        if (!StringUtils.isEmpty(origin) && !"null".equals(origin)) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        } else {
            response.setHeader("Access-Control-Allow-Origin", "*");
        }
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
            "x-requested-with, Origin, Content-Type, Accept, xfilesize, xfilename, xfilecategory, access_token");

        // options请求不做业务处理
        if (CorsFilter.OPTIONS_METHOD_NAME.equals(request.getMethod())) {
            String requestURI = request.getRequestURL().toString();
            log.debug("options请求：{}", requestURI);
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write("OK");
            return;
        }
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
