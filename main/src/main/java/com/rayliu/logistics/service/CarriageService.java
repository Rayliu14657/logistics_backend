package com.rayliu.logistics.service;

import com.rayliu.logistics.model.Carriage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rayliu.common.result.RestResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-18
 */
public interface CarriageService extends IService<Carriage> {
    RestResult findPage(int current, int limit);

    Carriage findByStatus(Integer status);

}
