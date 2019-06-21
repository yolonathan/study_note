package com.github.nicejing.security.service.impl;


import com.github.nicejing.security.entity.Admin;
import com.github.nicejing.security.mapper.AdminMapper;
import com.github.nicejing.security.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Nathan
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findByEmail(String email) {
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email", email);

        List<Admin> admins = adminMapper.selectByExample(example);
        return admins.size() > 0 ? admins.get(0) : null;
    }

    @Override
    public Admin findById(String id) {
        return adminMapper.selectByPrimaryKey(id);
    }
}
