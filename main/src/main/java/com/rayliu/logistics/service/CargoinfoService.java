package com.rayliu.logistics.service;


import com.rayliu.logistics.model.Cargoinfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rayliu.common.result.RestResult;

/**
 * <p>
 * 货物信息表 服务类
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-17
 */
public interface CargoinfoService extends IService<Cargoinfo> {
    RestResult findAllPage(int current, int limit);


}
