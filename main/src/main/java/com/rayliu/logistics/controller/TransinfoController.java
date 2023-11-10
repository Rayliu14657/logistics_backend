package com.rayliu.logistics.controller;

import com.rayliu.logistics.model.Transinfo;
import com.rayliu.logistics.service.DriverService;
import com.rayliu.logistics.service.TransinfoService;
import com.rayliu.common.result.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <p>
 * 运单信息表（记录运输中的信息） 前端控制器
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-18
 */
@Api(description = "运单信息管理模块")
@RestController
@RequestMapping("/transinfo")
public class TransinfoController {
    @Autowired
    private TransinfoService transinfoService;

    @Autowired
    private DriverService driverService;


    @ApiOperation(value="全部运单查询")
    @GetMapping("/page/{current}/{limit}")
    public RestResult findPage(
            @ApiParam(name="current",value = "当前页",required = true)
            @PathVariable int current,
            @ApiParam(name = "limit",value = "每页的记录数",required = true)
            @PathVariable int limit){
        return transinfoService.findAllPage(current,limit);
    }
    @ApiOperation(value = "单个运单的查询")
    @GetMapping("getById/{cargoID}")
    public RestResult getById(
            @ApiParam(name = "cargoID", value = "货单ID", required = true)
            @PathVariable int cargoID) {
        Transinfo flag = transinfoService.getById(cargoID);
        if (flag != null)
            return RestResult.ok().data("transinfo", flag);
        else
            return RestResult.error();
    }

    @ApiOperation(value="运单增加")
    @PostMapping("/add")
    public RestResult add(@RequestBody Transinfo transinfo) {
        Integer driverID=driverService.findNotBusy();
        if(driverID==null){
            return RestResult.error();
        }
        Transinfo flag = transinfoService.getById(transinfo.getId());
        if (flag == null) { // 数据库不存在该用户名
            transinfo.setDriverID(driverID);
            transinfoService.save(transinfo);
            return RestResult.ok();
        } else {
            return RestResult.error();
        }
    }

    @ApiOperation(value = "运单删除")
    @DeleteMapping ("{cargoID}")
    public RestResult delete(
            @ApiParam(name = "cargoID", value = "货单", required = true)
            @PathVariable int cargoID) {
        boolean flag = transinfoService.removeById(cargoID);
        if (flag)
            return RestResult.ok();
        else {
            return RestResult.error();
        }

    }
    @ApiOperation(value = "到货确认(运单修改)")
    @PostMapping("/update")
    public RestResult update(@RequestBody Transinfo transinfo) {
        boolean flag = transinfoService.updateById(transinfo);
        if (flag)
            return RestResult.ok();
        else
            return RestResult.error();
    }


    @ApiOperation(value = "确认到货")
    @PostMapping("/confirmArr/{id}")
    public RestResult confirmArr(
            @ApiParam(name = "id", value = "货单号", required = true)
            @PathVariable int id){
        Transinfo transinfo=transinfoService.getById(id);
        transinfo.setStatus(2);
        boolean flag=transinfoService.updateById(transinfo);
        if (flag)
            return RestResult.ok();
        else
            return RestResult.error();
    }

    @ApiOperation(value = "确认收货")
    @PostMapping("/confirmRec/{id}")
    public RestResult confirmRec(
            @ApiParam(name = "id", value = "货单号", required = true)
            @PathVariable int id){
        Transinfo transinfo=transinfoService.getById(id);
        transinfo.setStatus(3);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            transinfo.setToTime(sdf.parse(sdf.format(System.currentTimeMillis())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean flag=transinfoService.updateById(transinfo);
        if (flag)
            return RestResult.ok();
        else
            return RestResult.error();
    }
}




