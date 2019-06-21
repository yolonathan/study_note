package com.github.nicejing.security.mapper;

import com.github.nicejing.security.entity.Admin;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Nathan
 */
@Repository
public interface AdminMapper extends Mapper<Admin> {

    /**
     * 使用纯接口注解方式时
     * @param countryname
     * @return
     */
    // @Select("select * from country where countryname = #{countryname}")
    // User selectByCountryName(String countryname);

}
