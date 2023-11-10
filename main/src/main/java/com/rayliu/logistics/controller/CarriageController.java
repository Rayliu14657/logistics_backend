package com.rayliu.logistics.controller;

import com.rayliu.logistics.model.Carriage;
import com.rayliu.logistics.service.CarriageService;
import com.rayliu.common.result.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-18
 */
@Api(description = "运价信息管理模块")
@RestController
@RequestMapping("/carriage")
public class CarriageController {
    @Autowired
    private CarriageService carriageService;

    //分页查询所有运价
    @ApiOperation(value = "运价的分页查询")
    @PostMapping("/page/{current}/{limit}")
    public RestResult findPage(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable int current,
            @ApiParam(name = "limit", value = "每页的记录数", required = true)
            @PathVariable int limit,
            @RequestBody(required = false) Carriage carriage) {
        System.out.println("查询条件：" + carriage);
        return carriageService.findPage(current, limit);
    }

    //依字段查询单个运价
    @ApiOperation(value = "运费依状态查询")
    @GetMapping("getByStatus/{status}")
    public RestResult getByStatus(
            @ApiParam(name = "status", value = "地点状态", required = true)
            @PathVariable int status) {
        Carriage flag = carriageService.getById(status);
        if (flag != null)
            return RestResult.ok().data("status", flag);
        else
            return RestResult.error();
    }

    //运价修改控制器
    @ApiOperation(value = "运价的更新")
    @PostMapping("/update")
    public RestResult update(@RequestBody Carriage carriage){
        boolean flag = carriageService.updateById(carriage);
        if(flag){
            return RestResult.ok();
        }
        else {
            return RestResult.error();
        }
    }

    //运费状态增加控制器
    @ApiOperation(value = "管理员的增加")
    @PostMapping("/add")
    public RestResult add(@RequestBody Carriage carriage){
        Carriage flag = carriageService.findByStatus(carriage.getStatus());
        if(flag==null){
            carriageService.save(carriage);
            return RestResult.ok();
        }
        else {
            return RestResult.error();
        }
    }








}

