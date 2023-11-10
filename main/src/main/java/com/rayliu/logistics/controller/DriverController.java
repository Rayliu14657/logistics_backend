package com.rayliu.logistics.controller;


import com.rayliu.logistics.model.Driver;
import com.rayliu.logistics.service.DriverService;
import com.rayliu.common.result.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 司机表 前端控制器
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-18
 */
@Api(description = "司机信息管理模块")
@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    DriverService driverService;

    @ApiOperation(value = "增加司机信息")
    @PostMapping("/add")
    public RestResult add(@RequestBody Driver driver){
        Driver flag=driverService.findBydriverID(driver.getDriverID());
        if(flag==null){
            driverService.save(driver);
            return RestResult.ok();
        }
        else return RestResult.error();
    }

    @ApiOperation(value = "删除司机信息")
    @DeleteMapping("/delete/{driverID}")
    public RestResult delete(
            @ApiParam(name="driverID",value = "司机ID",required = true)
            @PathVariable int driverID)
    {
        Driver flag2=driverService.findBydriverID(driverID);
        boolean flag=driverService.removeById(driverID);
        if(flag&&flag2!=null)
            return RestResult.ok();
        else
            return RestResult.error();
    }

    @ApiOperation(value ="修改司机信息")
    @PostMapping("/update")
    public RestResult update(@RequestBody Driver driver){
        boolean flag=driverService.updateById(driver);
        if (flag)
            return RestResult.ok();
        else
            return RestResult.error();
    }

    @ApiOperation(value = "按ID查询司机信息")
    @GetMapping("/select/{driverID}")
    public RestResult select(
            @ApiParam(name="driverID",value = "司机ID",required = true)
            @PathVariable int driverID) {
        Driver driver=driverService.findBydriverID(driverID);
        if(driver!=null)
            return RestResult.ok().data("driver",driver);
        else return RestResult.error();
    }

    @ApiOperation(value = "分页查询所有司机信息")
    @PostMapping("/page/{current}/{limit}")
    public RestResult findPage(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable int current,
            @ApiParam(name = "limit", value = "每页的记录数", required = true)
            @PathVariable int limit) {
        return driverService.findAllPage(current,limit);
    }




}

