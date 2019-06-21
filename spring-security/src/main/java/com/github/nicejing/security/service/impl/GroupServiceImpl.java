package com.github.nicejing.security.service.impl;


import com.github.nicejing.security.entity.Group;
import com.github.nicejing.security.mapper.GroupMapper;
import com.github.nicejing.security.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Nathan
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public List<Group> findByIds(List<String> ids) {
        Example example = new Example(Group.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids);
        return groupMapper.selectByExample(example);
    }

    @Override
    public List<Group> findByParent(String parentId) {
        Example example = new Example(Group.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", parentId);
        return groupMapper.selectByExample(example);
    }

    @Override
    public List<Group> findAll() {
        return groupMapper.selectAll();
    }
}
