package com.github.nicejing.security.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Nathan
 */
@Data
@Table(name = "rbac_group_member")
public class GroupMember implements Serializable {
    private static final long serialVersionUID = -8053711078269750744L;
    @Id
    @Column(name = "`id`")
    private String            id;
    @Column(name = "`group_id`")
    private String            groupId;
    @Column(name = "`admin_id`")
    private String            adminId;
    @Column(name = "`create_datetime`")
    private LocalDateTime     createDatetime;
}
