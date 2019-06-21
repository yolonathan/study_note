package com.github.nicejing.security.security.support;


import com.github.nicejing.security.biz.AdminBiz;
import com.github.nicejing.security.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Nathan
 */
@Service("adminUserService")
public class AdminUserServiceImpl implements UserDetailsService {

    private static final String DEFAULT_ROLE_NAME = "ADMIN";

    @Autowired
    private AdminBiz adminBiz;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = adminBiz.findByEmail(username);
        if (admin == null) {
            throw new UsernameNotFoundException("用户名或密码不正确！");
        }

        AdminSecurityContext.getContext().set("id", admin.getId());
        return User.withUsername(admin.getUsername()).password(admin.getPassword()).accountExpired(false)
            .credentialsExpired(false).accountLocked(admin.getLock()).disabled(!admin.getEnable())
            .roles(DEFAULT_ROLE_NAME).build();
    }
}
