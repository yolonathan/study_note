package com.github.nicejing.security.service;


import com.github.nicejing.security.entity.Group;

import java.util.List;

/**
 * @author Nathan
 */
public interface GroupService {

    List<Group> findByIds(List<String> ids);

    List<Group> findByParent(String parentId);

    List<Group> findAll();
}
