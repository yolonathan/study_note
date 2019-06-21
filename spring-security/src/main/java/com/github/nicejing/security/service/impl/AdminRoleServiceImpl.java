package com.github.nicejing.security.service.impl;


import com.github.nicejing.security.entity.AdminRole;
import com.github.nicejing.security.mapper.AdminRoleMapper;
import com.github.nicejing.security.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Nathan
 */
@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public List<AdminRole> findByAdmin(String adminId) {
        Example example = new Example(AdminRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("adminId", adminId);
        return adminRoleMapper.selectByExample(example);
    }
}
