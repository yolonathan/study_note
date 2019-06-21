package com.github.nicejing.security.service;


import com.github.nicejing.security.entity.Role;

import java.util.List;

/**
 * @author Nathan
 */
public interface RoleService {

    List<Role> findByIds(List<String> ids);

    List<Role> findByParent(String parentId);

    List<Role> findAll();
}
