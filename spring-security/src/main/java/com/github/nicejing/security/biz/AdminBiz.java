package com.github.nicejing.security.biz;


import com.github.nicejing.security.entity.Admin;

/**
 * @author Nathan
 */
public interface AdminBiz {

    /**
     * 通过邮件地址查询管理员
     * @param email
     * @return
     */
    Admin findByEmail(String email);

    Admin findById(String id);


}
