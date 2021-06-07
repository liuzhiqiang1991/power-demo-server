package com.power.controller;

import com.power.common.api.CommonResult;
import com.power.dto.UmsAdminLoginParam;
import com.power.mbg.model.UmsAdmin;
import com.power.mbg.model.UmsPermission;
import com.power.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台用户管理
 * Created by macro on 2018/4/26.
 */
@Controller
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    private static Logger logger = LoggerFactory.getLogger(UmsAdminController.class);
    @Autowired
    private UmsAdminService adminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    //遗留问题 todo
    @ApiOperation(value = "查询数据")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String search2(String token){
        return "{\"code\":200,\"msg\":\"用户信息查询成功\",\"data\":{\"id\":24,\"username\":\"admin\",\"nickname\":\"nickname\",\"role\":\"role\",\"avatar\":\"avatar\",\"roles\":[\"role\"]}}";
    }

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsAdmin> adminAdd(String id, String username, String password, String icon, String email, String nickname, String note, String createtime, String logintime, String status) {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(Long.valueOf(id));
        umsAdmin.setUsername(username);
        umsAdmin.setPassword(password);
        umsAdmin.setIcon(icon);
        umsAdmin.setEmail(email);

        umsAdmin = adminService.register(umsAdmin);
        if (umsAdmin == null) {
            CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation(value = "删除数据")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsAdmin> deleteById(String id) {
        int i = adminService.deleteByID(Long.valueOf(id));
        if (i == -1) {
            CommonResult.failed();
        }
        return CommonResult.success(null);
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        System.out.print("token\t" + token);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }

    @ApiOperation(value = "根据id查询一条数据")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsAdmin>> searchById(String id){
        UmsAdmin umsAdmin = adminService.searchAdminById(Long.valueOf(id));
        List<UmsAdmin> umsAdminList = new ArrayList<>();
        umsAdminList.add(umsAdmin);
        return CommonResult.success(umsAdminList, umsAdminList.size());
    }

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam, BindingResult result) {
        logger.info("user is {}", umsAdminParam.toString());
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation(value = "更新数据")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> updateByID(@RequestBody UmsAdmin umsAdmin2) {
        logger.info("umsAdmin2 is {}", umsAdmin2.toString());
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(Long.valueOf(umsAdmin2.getId()));
        umsAdmin.setUsername(umsAdmin2.getUsername());
        umsAdmin.setPassword(umsAdmin2.getPassword());
        umsAdmin.setIcon(umsAdmin2.getIcon());
        umsAdmin.setEmail(umsAdmin2.getEmail());

        int i = adminService.updateByID(umsAdmin);
        if (i == -1) {
            CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }


}
