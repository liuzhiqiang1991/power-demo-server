package com.power.service.impl;

import com.github.pagehelper.PageHelper;
import com.power.mbg.mapper.PmsBrandMapper;
import com.power.mbg.model.PmsBrand;
import com.power.mbg.model.PmsBrandExample;
import com.power.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * PmsBrandService实现类
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    private static final Logger logger = LoggerFactory.getLogger(PmsBrandServiceImpl.class);

    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
        return brandMapper.selectByExample(new PmsBrandExample());
    }

}
