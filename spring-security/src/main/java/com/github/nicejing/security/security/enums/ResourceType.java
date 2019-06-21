package com.github.nicejing.security.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Nathan
 */
@Getter
@AllArgsConstructor
public enum ResourceType {
    /**
     * 菜单
     */
    MENU("menu"),
    /**
     * 按钮
     */
    BUTTON("button"),
    /**
     * 链接
     */
    URI("uri"),
    /**
     * 只有组才能关联角色，其他类型能不能关联角色
     * 角色
     */
    ROLE("role");

    private String name;
}
