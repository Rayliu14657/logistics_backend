package com.rayliu.logistics.service;

import com.rayliu.logistics.model.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rayliu.common.result.RestResult;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-17
 */
public interface AdminService extends IService<Admin> {
    Admin findByAdminID(int adminID);
    RestResult findPage(int current, int limit);

    Admin login(Admin admin);

}
