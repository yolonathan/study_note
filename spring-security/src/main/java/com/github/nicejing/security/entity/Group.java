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
@Table(name = "rbac_group")
public class Group implements Serializable {
    private static final long serialVersionUID = 826725344049546973L;

    @Id
    @Column(name = "`id`")
    private String            id;
    @Column(name = "`parent_id`")
    private String            parentId;
    @Column(name = "`is_parent`")
    private Boolean           isParent;
    @Column(name = "`name`")
    private String            name;
    @Column(name = "`code`")
    private String            code;
    @Column(name = "`description`")
    private String            description;
    @Column(name = "`create_datetime`")
    private LocalDateTime     createDatetime;
}
