package com.rayliu.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rayliu.logistics.model.Driver;
import com.rayliu.logistics.mapper.DriverMapper;
import com.rayliu.logistics.service.DriverService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rayliu.common.result.RestResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 司机表 服务实现类
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-18
 */
@Service
public class DriverServiceImpl extends ServiceImpl<DriverMapper, Driver> implements DriverService {
    @Override
    public Driver findBydriverID(int driverID){
        QueryWrapper<Driver> wrapper=new QueryWrapper<>();
        wrapper.eq("driverID",driverID);
        return baseMapper.selectOne(wrapper);
    }


    @Override
    public RestResult findAllPage(int current, int limit) {
        // 创建分页对象
        Page<Driver> page = new Page<>(current, limit);
        QueryWrapper<Driver> wrapper = new QueryWrapper<>();
        // 调用分页查询的方法
        baseMapper.selectPage(page, null);
        // 获取表中的显示的数据
        List<Driver> list = page.getRecords();
        // 获取表中总记录数
        long total = page.getTotal();
        // 获取分页总数
        long pages = page.getPages();
        // 将分页信息数据封装在Map集合中
        return RestResult.ok().data("rows", list).data("total", total).data("pages", pages).data("current", current).data("limit", limit);
    }

   /* public RestResult findPageByDriverID(int current, int limit){
        //创建分页对象
        Page<Driver> page=new Page<>(current,limit);
        //调用分页查询方法
        QueryWrapper<Driver> wrapper=new QueryWrapper<>();
        wrapper.isNull("driverID");
        baseMapper.selectPage(page,wrapper);
        //获取表中的显示数据
        List<Driver> list = page.getRecords();
        // 获取表中总记录数
        long total = page.getTotal();
        // 获取分页总数
        long pages = page.getPages();
        // 将分页信息数据封装在Map集合中
        return RestResult.ok().data("rows", list).data("total", total).data("pages", pages).data("current", current).data("limit", limit);

    }*/

    @Override
    public Integer findNotBusy(){
        QueryWrapper<Driver> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("busy",0).last("limit 1");
        if(baseMapper.selectOne(queryWrapper)==null){
            return null;
        }
        Driver driver=baseMapper.selectOne(queryWrapper);
        driver.setBusy(1);
        updateById(driver);
        return driver.getDriverID();
    }


}
