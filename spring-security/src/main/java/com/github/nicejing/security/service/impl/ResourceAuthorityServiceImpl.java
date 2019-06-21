package com.github.nicejing.security.service.impl;


import com.github.nicejing.security.entity.ResourceAuthority;
import com.github.nicejing.security.mapper.ResourceAuthorityMapper;
import com.github.nicejing.security.service.ResourceAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Nathan
 */
@Service
public class ResourceAuthorityServiceImpl implements ResourceAuthorityService {

    @Autowired
    private ResourceAuthorityMapper resourceAuthorityMapper;

    @Override
    public List<ResourceAuthority> findList(List<String> authorityId, String authorityType,
                                            List<String> resourceId, String resourceType) {
        Example example = new Example(ResourceAuthority.class);
        Example.Criteria criteria = example.createCriteria();
        if (authorityId != null && authorityId.size() > 0) {
            criteria.andEqualTo("authorityId", authorityId);
        }
        if (authorityType != null) {
            criteria.andEqualTo("authorityType", authorityType);
        }
        if (resourceType != null) {
            criteria.andEqualTo("resourceType", resourceType);
        }
        if (resourceId != null && resourceId.size() > 0) {
            criteria.andEqualTo("resourceId", resourceId);
        }

        return resourceAuthorityMapper.selectByExample(example);
    }
}
