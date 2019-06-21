package com.github.nicejing.security.biz.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.nicejing.security.biz.GroupBiz;
import com.github.nicejing.security.entity.Group;
import com.github.nicejing.security.entity.GroupMember;
import com.github.nicejing.security.service.GroupMemberService;
import com.github.nicejing.security.service.GroupService;

/**
 * @author Nathan
 */
@Service
public class GroupBizImpl implements GroupBiz {

    @Autowired
    private GroupMemberService groupMemberService;
    @Autowired
    private GroupService       groupService;

    @Override
    public List<Group> findByAdmin(String adminId) {
        Set<Group> result = new HashSet<>();
        List<GroupMember> groupMemberList = groupMemberService.findByAdmin(adminId);
        if (groupMemberList.size() > 0) {
            List<String> groupIds = groupMemberList.stream().map(GroupMember::getGroupId).collect(Collectors.toList());
            List<Group> groups = groupService.findByIds(groupIds);
            result.addAll(groups);
            for (Group group : groups) {
                if (group.getIsParent()) {
                    List<Group> groupList = groupService.findByParent(group.getId());
                    result.addAll(groupList);
                }
            }
        }
        return new ArrayList<>(result);
    }

    @Override
    public List<Group> findAll() {
        return groupService.findAll();
    }
}
