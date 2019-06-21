package com.github.nicejing.security.service.impl;


import com.github.nicejing.security.entity.GroupMember;
import com.github.nicejing.security.mapper.GroupMemberMapper;
import com.github.nicejing.security.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Nathan
 */
@Service
public class GroupMemberServiceImpl implements GroupMemberService {

    @Autowired
    private GroupMemberMapper groupMemberMapper;

    @Override
    public List<GroupMember> findByAdmin(String adminId) {
        Example example = new Example(GroupMember.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("adminId", adminId);
        return groupMemberMapper.selectByExample(example);

    }
}
