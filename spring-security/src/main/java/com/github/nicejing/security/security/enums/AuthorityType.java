package com.github.nicejing.security.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Nathan
 */
@Getter
@AllArgsConstructor
public enum AuthorityType {

    /**
     * 组权限
     */
    GROUP("group"),
    /**
     * 角色权限
     */
    ROLE("role"),
    /**
     * 个人权限
     */
    PERSON("person");

    private String name;
}
