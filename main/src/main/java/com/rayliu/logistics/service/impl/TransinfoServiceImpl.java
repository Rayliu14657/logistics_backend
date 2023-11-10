package com.rayliu.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rayliu.logistics.model.LocationInfo;
import com.rayliu.logistics.model.Transinfo;
import com.rayliu.logistics.mapper.TransinfoMapper;
import com.rayliu.logistics.service.TransinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rayliu.common.result.RestResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 运单信息表（记录运输中的信息） 服务实现类
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-18
 */
@Service
public class TransinfoServiceImpl extends ServiceImpl<TransinfoMapper, Transinfo> implements TransinfoService {
    @Override
    public RestResult findAllPage(int current, int limit) {
        // 创建分页对象
        Page<Transinfo> page = new Page<>(current, limit);
        QueryWrapper<Transinfo> wrapper = new QueryWrapper<>();
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
        List<Transinfo> list = page.getRecords();
        // 获取表中总记录数
        long total = page.getTotal();
        // 获取分页总数
        long pages = page.getPages();
        // 将分页信息数据封装在Map集合中
        return RestResult.ok().data("rows", list).data("total", total).data("pages", pages).data("current", current).data("limit", limit);
    }

    @Override
    public LocationInfo getLocation(Transinfo transinfo) {
        LocationInfo locationInfo = new LocationInfo();
        locationInfo.setCargoID(transinfo.getId());
        locationInfo.setFromWhere(transinfo.getFromSpot());
        locationInfo.setToWhere(transinfo.getToSpot());
        return locationInfo;
    }

}
