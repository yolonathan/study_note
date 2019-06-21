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
@Table(name = "rbac_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 6550985258958462920L;

    @Id
    @Column(name = "`id`")
    private String            id;
    @Column(name = "`parent_id`")
    private String            parentId;
    @Column(name = "`is_parent`")
    private Boolean           isParent;
    @Column(name = "`role_name`")
    private String            roleName;
    @Column(name = "`role_code`")
    private String            roleCode;
    @Column(name = "`status`")
    private Boolean           status;
    @Column(name = "`create_datetime`")
    private LocalDateTime     createDatetime;
}
