package com.rayliu.logistics.service;

import com.rayliu.logistics.model.Driver;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rayliu.common.result.RestResult;

/**
 * <p>
 * 司机表 服务类
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-18
 */
public interface DriverService extends IService<Driver> {
    Driver findBydriverID(int driverID);

    RestResult findAllPage(int current, int limit);

    Integer findNotBusy();


}
