package com.github.nicejing.security.service;


import com.github.nicejing.security.entity.ResourceAuthority;

import java.util.List;

/**
 * @author Nathan
 */
public interface ResourceAuthorityService {

    List<ResourceAuthority> findList(List<String> authorityId, String authorityType, List<String> resourceId, String resourceType);
}
