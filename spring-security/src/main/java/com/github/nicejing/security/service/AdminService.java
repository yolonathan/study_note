package com.github.nicejing.security.service;


import com.github.nicejing.security.entity.Admin;

/**
 * @author Nathan
 */
public interface AdminService {

    /**
     * 查找管理员
     * @param email
     * @return
     */
    Admin findByEmail(String email);


    Admin findById(String id);
}
