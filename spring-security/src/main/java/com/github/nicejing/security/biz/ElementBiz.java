package com.github.nicejing.security.biz;


import com.github.nicejing.security.entity.Element;

import java.util.List;

/**
 * @author Nathan
 */
public interface ElementBiz {

    List<Element> findAllByAdmin(String adminId, List<String> roleIds, List<String> groupIds);

    List<Element> findByAdmin(String adminId);

    List<Element> findByGroup(List<String> groupIds);

    List<Element> findByRole(List<String> roleIds);

    List<Element> findAll();
}
