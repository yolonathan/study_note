package com.github.nicejing.security.service;


import com.github.nicejing.security.entity.Element;

import java.util.List;

/**
 * @author Nathan
 */
public interface ElementService {

    List<Element> findByIds(List<String> ids);

    List<Element> findAll();
}
