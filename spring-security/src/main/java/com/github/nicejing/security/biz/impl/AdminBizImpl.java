package com.github.nicejing.security.biz.impl;


import com.github.nicejing.security.biz.AdminBiz;
import com.github.nicejing.security.entity.Admin;
import com.github.nicejing.security.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Nathan
 */
@Service
public class AdminBizImpl implements AdminBiz {

    @Autowired
    private AdminService adminService;

    @Override
    public Admin findByEmail(String email) {
        return adminService.findByEmail(email);
    }

    @Override
    public Admin findById(String id) {
        return adminService.findById(id);
    }
}
