package com.rayliu.logistics.service;

import com.rayliu.logistics.model.Finance;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rayliu.common.result.RestResult;

/**
 * <p>
 * 财务表 服务类
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-18
 */
public interface FinanceService extends IService<Finance> {

    Finance findId(int id);

    RestResult findAllPage(int current, int limit);
    /*RestResult findPage(int current, int limit, Finance finance)*/;

    RestResult findNullSum(int current,int limit);


}
