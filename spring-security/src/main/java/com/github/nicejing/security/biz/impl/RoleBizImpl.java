package com.github.nicejing.security.biz.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.nicejing.security.biz.GroupBiz;
import com.github.nicejing.security.biz.RoleBiz;
import com.github.nicejing.security.entity.AdminRole;
import com.github.nicejing.security.entity.Group;
import com.github.nicejing.security.entity.ResourceAuthority;
import com.github.nicejing.security.entity.Role;
import com.github.nicejing.security.security.enums.AuthorityType;
import com.github.nicejing.security.security.enums.ResourceType;
import com.github.nicejing.security.service.AdminRoleService;
import com.github.nicejing.security.service.ResourceAuthorityService;
import com.github.nicejing.security.service.RoleService;

/**
 * @author Nathan
 */
@Service
public class RoleBizImpl implements RoleBiz {

    @Autowired
    private AdminRoleService         adminRoleService;
    @Autowired
    private RoleService              roleService;
    @Autowired
    private GroupBiz                 groupBiz;
    @Autowired
    private ResourceAuthorityService resourceAuthorityService;

    @Override
    public List<Role> findByAdmin(String adminId) {
        // 用户所在的组
        Set<Role> result = new HashSet<>();
        List<AdminRole> adminRoleList = adminRoleService.findByAdmin(adminId);
        if (adminRoleList.size() > 0) {
            List<String> roleIds = adminRoleList.stream().map(AdminRole::getRoleId).collect(Collectors.toList());
            Set<Role> role = getRole(roleIds);
            result.addAll(role);
        }

        // 在一个组中设置了角色
        List<Group> groups = groupBiz.findByAdmin(adminId);
        List<String> groupIds = groups.stream().map(Group::getId).collect(Collectors.toList());

        List<ResourceAuthority> resourceAuthorities = resourceAuthorityService.findList(groupIds,
            AuthorityType.GROUP.getName(), null, ResourceType.ROLE.getName());

        if (resourceAuthorities.size() > 0) {
            List<String> roleIds = resourceAuthorities.stream().map(ResourceAuthority::getResourceId)
                .collect(Collectors.toList());
            Set<Role> role = getRole(roleIds);
            result.addAll(role);
        }

        return new ArrayList<>(result);
    }

    @Override
    public List<Role> findAll() {
        return roleService.findAll();
    }

    private Set<Role> getRole(List<String> roleIds) {
        List<Role> roles = roleService.findByIds(roleIds);
        Set<Role> result = new HashSet<>(roles);
        for (Role role : roles) {
            if (role.getIsParent()) {
                List<Role> roleList = roleService.findByParent(role.getId());
                result.addAll(roleList);
            }
        }
        return result;
    }
}
