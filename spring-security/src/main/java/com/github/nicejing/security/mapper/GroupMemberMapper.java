package com.github.nicejing.security.mapper;

import com.github.nicejing.security.entity.GroupMember;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Nathan
 */
@Repository
public interface GroupMemberMapper extends Mapper<GroupMember> {

}
