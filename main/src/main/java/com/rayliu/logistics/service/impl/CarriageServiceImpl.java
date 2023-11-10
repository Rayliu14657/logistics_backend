package com.rayliu.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rayliu.logistics.model.Carriage;
import com.rayliu.logistics.mapper.CarriageMapper;
import com.rayliu.logistics.service.CarriageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rayliu.common.result.RestResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-18
 */
@Service
public class CarriageServiceImpl extends ServiceImpl<CarriageMapper, Carriage> implements CarriageService {
    @Override
    public RestResult findPage(int current, int limit) {
        Page<Carriage> page = new Page<>(current,limit);
        //调用分页查询方法
        baseMapper.selectPage(page,null);
        //获取表中显示的数据
        List<Carriage> list = page.getRecords();
        //获取表中的总记录数
        long total = page.getTotal();
        //获取分页数
        long pages = page.getPages();
        return RestResult.ok()
                .data("rows", list)
                .data("total", total)
                .data("pages", pages)
                .data("current", current)
                .data("limit", limit);
    }

    @Override
    public Carriage findByStatus(Integer status) {
        QueryWrapper<Carriage> wrapper = new QueryWrapper<>();
        wrapper.eq("status",status);
        return baseMapper.selectOne(wrapper);
    }
}
