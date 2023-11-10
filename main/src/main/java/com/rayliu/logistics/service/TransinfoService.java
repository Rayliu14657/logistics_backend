package com.rayliu.logistics.service;

import com.rayliu.logistics.model.LocationInfo;
import com.rayliu.logistics.model.Transinfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rayliu.common.result.RestResult;

/**
 * <p>
 * 运单信息表（记录运输中的信息） 服务类
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-18
 */
public interface TransinfoService extends IService<Transinfo> {
    RestResult findAllPage(int current, int limit);

    LocationInfo getLocation(Transinfo transinfo);


}
