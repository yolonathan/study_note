package com.github.nicejing.security.security.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Nathan
 */
@Data
public class PermissionDTO implements Serializable {

    private static final long serialVersionUID = -169432187839128694L;

    /**
     * 菜单还是按钮
     */
    private String            type;

    /**
     * 标识码
     */
    private String            code;
    /**
     * 描述
     */
    private String            name;

    private String            uri;

    private String            method;
}
