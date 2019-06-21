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
@Table(name = "rbac_admin")
@Data
public class Admin implements Serializable {
    private static final long serialVersionUID = 7598919585008138332L;

    @Id
    @Column(name = "`id`")
    private String            id;
    @Column(name = "`email`")
    private String            email;
    @Column(name = "`username`")
    private String            username;
    @Column(name = "`password`")
    private String            password;
    @Column(name = "`lock`")
    private Boolean           lock;
    @Column(name = "`enable`")
    private Boolean           enable;
    @Column(name = "`tel`")
    private String            tel;
    @Column(name = "`create_datetime`")
    private LocalDateTime     createDatetime;
}
