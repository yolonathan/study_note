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
@Table(name = "rbac_admin_role")
public class AdminRole implements Serializable {
    private static final long serialVersionUID = 6550985258958462920L;

    @Id
    @Column(name = "`id`")
    private String            id;
    @Column(name = "`admin_id`")
    private String            adminId;
    @Column(name = "`role_id`")
    private String            roleId;
    @Column(name = "`create_datetime`")
    private LocalDateTime     createDatetime;

}
