package com.rayliu.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rayliu.logistics.model.Admin;
import com.rayliu.logistics.mapper.AdminMapper;
import com.rayliu.logistics.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rayliu.common.result.RestResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-17
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Override
    public Admin findByAdminID(int adminID) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("adminID",adminID);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public RestResult findPage(int current, int limit) {
        //创建分页对象
        Page<Admin> page = new Page<>(current,limit);
        //调用分页查询方法
        baseMapper.selectPage(page,null);
        //获取表中显示的数据
        List<Admin> list = page.getRecords();
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
    public Admin login(Admin admin) {
        QueryWrapper<Admin> wrapper  =new QueryWrapper<>();
        wrapper.eq("adminID",admin.getAdminID());
        wrapper.eq("password",admin.getPassword());
        return baseMapper.selectOne(wrapper);
    }
}
