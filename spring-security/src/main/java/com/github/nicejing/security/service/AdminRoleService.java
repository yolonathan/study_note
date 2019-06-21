package com.github.nicejing.security.service;


import com.github.nicejing.security.entity.AdminRole;

import java.util.List;

/**
 * @author Nathan
 */
public interface AdminRoleService {

    List<AdminRole> findByAdmin(String adminId);
}
