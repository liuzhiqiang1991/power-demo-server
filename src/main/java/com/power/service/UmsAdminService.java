package com.power.service;

import com.power.mbg.model.UmsAdmin;
import com.power.mbg.model.UmsPermission;

import java.util.List;

/**
 * 后台管理员Service
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<UmsPermission> getPermissionList(Long adminId);

    /**
     * 列出用户
     */
    List<UmsAdmin> listAdmin(Integer pageNum, Integer pageSize);
    List<UmsAdmin> listAdminWhere(Integer pageNum, Integer pageSize, String username);
    int updateByID(UmsAdmin umsAdmin);
    int deleteByID(Long id);

    UmsAdmin searchAdminById(long id);

}
