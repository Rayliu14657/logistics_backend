package com.rayliu.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rayliu.logistics.mapper.FinanceMapper;
import com.rayliu.logistics.model.Finance;
import com.rayliu.logistics.service.FinanceService;
import com.rayliu.common.result.RestResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 财务表 服务实现类
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-18
 */
@Service
public class FinanceServiceImpl extends ServiceImpl<FinanceMapper, Finance> implements FinanceService {
    @Override
    public Finance findId(int id){
        QueryWrapper<Finance> wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);

        return baseMapper.selectOne(wrapper);
    }

    @Override
    public RestResult findAllPage(int current, int limit) {
        // 创建分页对象
        Page<Finance> page = new Page<>(current, limit);
        // 调用分页查询的方法
        baseMapper.selectPage(page, null);
        // 获取表中的显示的数据
        List<Finance> list = page.getRecords();
        // 获取表中总记录数
        long total = page.getTotal();
        // 获取分页总数
        long pages = page.getPages();
        // 将分页信息数据封装在Map集合中
        return RestResult.ok().data("rows", list).data("total", total).data("pages", pages).data("current", current).data("limit", limit);
    }



    @Override
    public RestResult findNullSum(int current, int limit) {
            //创建分页对象
            Page<Finance> page=new Page<>(current,limit);
            //调用分页查询方法
            QueryWrapper<Finance> wrapper=new QueryWrapper<>();
            wrapper.isNull("sum");
            baseMapper.selectPage(page,wrapper);
            //获取表中的显示数据
            List<Finance> list = page.getRecords();
            // 获取表中总记录数
            long total = page.getTotal();
            // 获取分页总数
            long pages = page.getPages();
            // 将分页信息数据封装在Map集合中
            return RestResult.ok().data("rows", list).data("total", total).data("pages", pages).data("current", current).data("limit", limit);

    }
}


