package com.github.nicejing.security.biz.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.nicejing.security.biz.ElementBiz;
import com.github.nicejing.security.entity.Element;
import com.github.nicejing.security.entity.ResourceAuthority;
import com.github.nicejing.security.security.enums.AuthorityType;
import com.github.nicejing.security.security.enums.ResourceType;
import com.github.nicejing.security.service.ElementService;
import com.github.nicejing.security.service.ResourceAuthorityService;

/**
 * @author Nathan
 */
@Service
public class ElementBizImpl implements ElementBiz {

    @Autowired
    private ResourceAuthorityService resourceAuthorityService;
    @Autowired
    private ElementService           elementService;

    @Override
    public List<Element> findAllByAdmin(String adminId, List<String> roleIds, List<String> groupIds) {
        // 个人权限
        List<Element> personElements = findByAdmin(adminId);
        Set<Element> result = new HashSet<>(personElements);
        if (groupIds != null && groupIds.size() > 0) {
            // 组权限
            List<Element> groupElement = findByGroup(groupIds);
            result.addAll(groupElement);
        }

        if (roleIds != null && roleIds.size() > 0) {
            // 角色权限
            List<Element> roleElement = findByRole(roleIds);
            result.addAll(roleElement);
        }

        return new ArrayList<>(result);
    }

    @Override
    public List<Element> findByAdmin(String adminId) {

        List<Element> result = new ArrayList<>();
        List<ResourceAuthority> resourceAuthorities = resourceAuthorityService
            .findList(Collections.singletonList(adminId), AuthorityType.PERSON.getName(), null, null);
        if (resourceAuthorities.size() > 0) {
            List<String> elementIds = resourceAuthorities.stream().map(ResourceAuthority::getResourceId)
                .collect(Collectors.toList());
            List<Element> elements = elementService.findByIds(elementIds);
            result.addAll(elements);

        }
        return result;
    }

    @Override
    public List<Element> findByGroup(List<String> groupIds) {

        List<Element> result = new ArrayList<>();
        List<ResourceAuthority> resourceAuthorities = resourceAuthorityService.findList(groupIds,
            AuthorityType.GROUP.getName(), null, null);

        if (resourceAuthorities.size() > 0) {
            List<ResourceAuthority> authorities = resourceAuthorities.stream()
                .filter(resource -> !resource.getAuthorityType().equalsIgnoreCase(ResourceType.ROLE.getName()))
                .collect(Collectors.toList());
            if (authorities.size() > 0) {

                List<String> elementIds = authorities.stream().map(ResourceAuthority::getResourceId)
                    .collect(Collectors.toList());
                result = elementService.findByIds(elementIds);
            }

        }

        return result;
    }

    @Override
    public List<Element> findByRole(List<String> roleIds) {
        List<Element> result = new ArrayList<>();
        List<ResourceAuthority> resourceAuthorities = resourceAuthorityService.findList(roleIds,
            AuthorityType.ROLE.getName(), null, null);
        if (resourceAuthorities.size() > 0) {
            List<String> elementIds = resourceAuthorities.stream().map(ResourceAuthority::getResourceId)
                .collect(Collectors.toList());
            result = elementService.findByIds(elementIds);
        }
        return result;
    }

    @Override
    public List<Element> findAll() {
        return elementService.findAll();
    }
}
