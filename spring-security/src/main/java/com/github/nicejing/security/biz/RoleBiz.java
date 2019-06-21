package com.github.nicejing.security.biz;


import com.github.nicejing.security.entity.Role;

import java.util.List;

/**
 * @author Nathan
 */
public interface RoleBiz {

    List<Role> findByAdmin(String adminId);

    List<Role> findAll();
}
