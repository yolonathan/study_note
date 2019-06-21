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
@Table(name = "rbac_resource_authority")
public class ResourceAuthority implements Serializable {
    private static final long serialVersionUID = -4973066007763703441L;

    @Id
    @Column(name = "`id`")
    private String            id;

    @Column(name = "`authority_id`")
    private String            authorityId;

    @Column(name = "`authority_type`")
    private String            authorityType;
    @Column(name = "`resource_id`")
    private String            resourceId;
    @Column(name = "`resource_type`")
    private String            resourceType;
    @Column(name = "`create_datetime`")
    private LocalDateTime     createDatetime;

}
