package com.github.nicejing.security.biz;


import com.github.nicejing.security.entity.Group;

import java.util.List;

/**
 * @author Nathan
 */
public interface GroupBiz {

    List<Group> findByAdmin(String adminId);

    List<Group> findAll();

}
