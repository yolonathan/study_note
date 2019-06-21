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
@Table(name = "rbac_element")
public class Element implements Serializable {
    private static final long serialVersionUID = 4830383530395020321L;

    @Id
    @Column(name = "`id`")
    private String            id;

    @Column(name = "`type`")
    private String            type;

    @Column(name = "`code`")
    private String            code;

    @Column(name = "`name`")
    private String            name;

    @Column(name = "`uri`")
    private String            uri;

    @Column(name = "`method`")
    private String            method;

    @Column(name = "`description`")
    private String            description;

    @Column(name = "`create_datetime`")
    private LocalDateTime     createDatetime;

}
