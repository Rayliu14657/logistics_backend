package com.rayliu.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rayliu.logistics.model.Cargoinfo;
import com.rayliu.logistics.mapper.CargoinfoMapper;
import com.rayliu.logistics.service.CargoinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rayliu.common.result.RestResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 货物信息表 服务实现类
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-17
 */
@Service
public class CargoinfoServiceImpl extends ServiceImpl<CargoinfoMapper, Cargoinfo> implements CargoinfoService {
    @Override
    public RestResult findAllPage(int current, int limit) {
        // 创建分页对象
        Page<Cargoinfo> page = new Page<>(current, limit);
        QueryWrapper<Cargoinfo> wrapper = new QueryWrapper<>();
        // 条件构造器——构造条件
       /*
        if (!StringUtils.isEmpty(employee.getName()))
            wrapper.like("name", employee.getName());
        if (!StringUtils.isEmpty(employee.getSex()) && employee.getSex() != 0)
            wrapper.eq("sex", employee.getSex());
        if (!StringUtils.isEmpty(employee.getLevel()) && employee.getLevel() != 0)
            wrapper.eq("level", employee.getLevel());
        if (!StringUtils.isEmpty(employee.getAge()))
            wrapper.ge("age", employee.getAge());
        if (!StringUtils.isEmpty(employee.getWorkTime()))
            wrapper.ge("workTime", employee.getWorkTime());
        if (!StringUtils.isEmpty(employee.getGmtCreate()))
            wrapper.ge("gmt_create", employee.getGmtCreate());
        */
        // 调用分页查询的方法
        baseMapper.selectPage(page, null);
        // 获取表中的显示的数据
        List<Cargoinfo> list = page.getRecords();
        // 获取表中总记录数
        long total = page.getTotal();
        // 获取分页总数
        long pages = page.getPages();
        // 将分页信息数据封装在Map集合中
        return RestResult.ok().data("rows", list).data("total", total).data("pages", pages).data("current", current).data("limit", limit);
    }


}
