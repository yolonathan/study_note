package com.github.nicejing.security.service.impl;


import com.github.nicejing.security.entity.Role;
import com.github.nicejing.security.mapper.RoleMapper;
import com.github.nicejing.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Nathan
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findByIds(List<String> ids) {
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids);
        return roleMapper.selectByExample(example);
    }

    @Override
    public List<Role> findByParent(String parentId) {
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", parentId);
        return roleMapper.selectByExample(example);
    }

    @Override
    public List<Role> findAll() {
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", true);
        return roleMapper.selectByExample(example);
    }
}
