package com.github.nicejing.security.service;


import com.github.nicejing.security.entity.GroupMember;

import java.util.List;

/**
 * @author Nathan
 */
public interface GroupMemberService {

    List<GroupMember> findByAdmin(String adminId);
}
