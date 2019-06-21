package com.github.nicejing.security.service.impl;


import com.github.nicejing.security.entity.Element;
import com.github.nicejing.security.mapper.ElementMapper;
import com.github.nicejing.security.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Nathan
 */
@Service
public class ElementServiceImpl implements ElementService {

    @Autowired
    private ElementMapper elementMapper;

    @Override
    public List<Element> findByIds(List<String> ids) {

        Example example = new Example(Element.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids);
        return elementMapper.selectByExample(example);
    }

    @Override
    public List<Element> findAll() {
        return elementMapper.selectAll();
    }

}
