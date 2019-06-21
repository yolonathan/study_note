package com.github.nicejing.security.security.support;


import com.github.nicejing.security.security.dto.AdminInfoDTO;
import com.github.nicejing.security.security.dto.PermissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Nathan
 */
@Service("adminPermissionService")
public class AdminPermissionService {

    @Autowired
    private AntPathMatcher antPathMatcher;

    public boolean onAuthentication(HttpServletRequest request, Authentication authentication) {
        boolean hasPermission = false;

        Object principal = authentication.getPrincipal();
        if (principal instanceof AdminInfoDTO) {
            AdminInfoDTO info = (AdminInfoDTO) principal;
            List<PermissionDTO> uris = info.getUris();
            for (PermissionDTO dto : uris) {
                if (antPathMatcher.match(dto.getUri(), request.getRequestURI())
                    && request.getMethod().equalsIgnoreCase(dto.getMethod())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
