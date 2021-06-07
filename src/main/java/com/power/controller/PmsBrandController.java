package com.power.controller;

import com.power.common.api.CommonPage;
import com.power.common.api.CommonResult;
import com.power.mbg.model.PmsBrand;
import com.power.service.PmsBrandService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 品牌管理Controller
 */
@Controller
@RequestMapping("/brand")
public class PmsBrandController {
    private static final Logger logger = LoggerFactory.getLogger(PmsBrandController.class);
    @Autowired
    private PmsBrandService pmsBrandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(pmsBrandService.listAllBrand());
    }
}
