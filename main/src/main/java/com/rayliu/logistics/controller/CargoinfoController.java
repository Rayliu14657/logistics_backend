package com.rayliu.logistics.controller;

import com.rayliu.logistics.model.*;
import com.rayliu.logistics.service.*;
import com.rayliu.common.result.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * 货物信息表 前端控制器
 * </p>
 *
 * @author XXX
 * @since 2022-07-17
 */
@Api(description = "货物信息管理模块")
@RestController
@RequestMapping("/cargoinfo")
@CrossOrigin
public class CargoinfoController {
    @Autowired
    private CargoinfoService cargoinfoService;

    @Autowired
    private TransinfoService transinfoService;

    @Autowired
    private FinanceService financeService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private CarriageService carriageService;

    @ApiOperation(value="全部货单查询")
    @GetMapping("/page/{current}/{limit}")
    public RestResult findPage(
            @ApiParam(name="current",value = "当前页",required = true)
            @PathVariable int current,
            @ApiParam(name = "limit",value = "每页的记录数",required = true)
            @PathVariable int limit){
        return cargoinfoService.findAllPage(current,limit);
    }


    @ApiOperation(value = "单个货单的查询")
    @GetMapping("getById/{id}")
    public RestResult getById(
            @ApiParam(name = "id", value = "货单ID", required = true)
            @PathVariable int id) {
        Cargoinfo flag = cargoinfoService.getById(id);
        if (flag != null)
            return RestResult.ok().data("cargoinfo", flag);
        else
            return RestResult.error();
    }

    @ApiOperation(value="新建货票")
    @PostMapping("/add")
    public RestResult add(@RequestBody Cargoinfo cargoinfo) {
        /*Cargoinfo flag = cargoinfoService.getById(cargoinfo.getId());
        Integer driverID=driverService.findNotBusy();
        if(driverID==null){
            return RestResult.error();
        }
        else {
            if (flag == null) { // 数据库不存在该id
                cargoinfoService.save(cargoinfo);
                Transinfo transinfo = new Transinfo();
                transinfo.setId(cargoinfo.getId());
                transinfo.setDriverID(driverID);
                transinfoService.save(transinfo);
                Finance finance = new Finance();
                finance.setId(cargoinfo.getId());
                financeService.save(finance);
                return RestResult.ok();
            } else {
                return RestResult.error();
            }
        }*/
        Cargoinfo flag=cargoinfoService.getById(cargoinfo.getId());
        if(flag==null){
            cargoinfoService.save(cargoinfo);
            return RestResult.ok();
        }
        else return RestResult.error();
    }

    @ApiOperation(value = "货票的删除")
    @DeleteMapping("{id}")
    public RestResult delete(
            @ApiParam(name = "id", value = "货单", required = true)
            @PathVariable int id) {
        boolean flag = cargoinfoService.removeById(id);
        if (flag)
            return RestResult.ok();
        else
            return RestResult.error();
    }

    @ApiOperation(value = "货票修改")
    @PostMapping("/update")
    public RestResult update(@RequestBody Cargoinfo cargoinfo) {
        boolean flag = cargoinfoService.updateById(cargoinfo);
        if(flag){
            return RestResult.ok();
        }
        else {
            return RestResult.error();
        }
    }

/*
    @ApiOperation(value = "退货订单生成")
    @PostMapping("/returned{id}")
    public RestResult returnGoods(
            @ApiParam(name = "id", value = "货单号", required = true)
            @PathVariable int id
    ){
        Cargoinfo cargoinfo = cargoinfoService.getById(id);
        if(cargoinfo==null){
            return RestResult.error();
        }
        else {
            cargoinfoService.save(cargoinfo);

            Transinfo transinfo = transinfoService.getById(id);
            String fromP = transinfo.getFromProvince();
            String toP = transinfo.getToProvince();
            String fromS = transinfo.getFromSpot();
            String toS = transinfo.getToSpot();
            transinfo.setId(cargoinfo.getId());
            transinfo.setFromProvince(toP);
            transinfo.setToProvince(fromP);
            transinfo.setFromSpot(toS);
            transinfo.setToSpot(fromS);
            transinfo.setStatus(0);
            transinfoService.save(transinfo);

            Finance finance  =financeService.getById(id);
            finance.setId(cargoinfo.getId());
            financeService.save(finance);
            return RestResult.ok();
        }
    }*/
@ApiOperation(value = "退货订单生成")
@PostMapping("/returned{id}")
public RestResult returnGoods(
        @ApiParam(name = "id", value = "货单号", required = true)
        @PathVariable int id
){
    Cargoinfo cargoinfo = cargoinfoService.getById(id);
    if(cargoinfo==null){
        return RestResult.error();
    }
    else {
        cargoinfoService.save(cargoinfo);

        Transinfo transinfo = transinfoService.getById(id);
        String fromP = transinfo.getFromProvince();
        String toP = transinfo.getToProvince();
        String fromS = transinfo.getFromSpot();
        String toS = transinfo.getToSpot();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 2);
        Date returnTime = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            transinfo.setFromTime(sdf.parse(sdf.format(System.currentTimeMillis())));
            System.out.println(transinfo.getFromTime());
            transinfo.setToTime(null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        transinfo.setId(cargoinfo.getId());
        transinfo.setFromProvince(toP);
        transinfo.setToProvince(fromP);
        transinfo.setFromSpot(toS);
        transinfo.setToSpot(fromS);
        transinfo.setStatus(0);
        transinfoService.save(transinfo);

        Finance finance  =financeService.getById(id);
        finance.setId(cargoinfo.getId());
        financeService.save(finance);
        return RestResult.ok();
    }
}
    @ApiOperation(value = "运费计算")
    @PostMapping("/getcarriage{id}")
    public RestResult giveCarriage(
        @ApiParam(name = "id", value = "货单号", required = true)
        @PathVariable int id){
        Cargoinfo cargoinfo = cargoinfoService.getById(id);
        if(cargoinfo==null){
            return RestResult.error();
        }
        else {
            Transinfo transinfo = transinfoService.getById(id);
            String fromP = transinfo.getFromProvince();
            String toP = transinfo.getToProvince();
            String fromS = transinfo.getFromSpot();
            String toS = transinfo.getToSpot();
            int status;
            if(fromP.equals(toP)){
                if(fromS.equals(toS)){
                    status=1;//市内
                }
                else{
                    status=2;//省内
                }
            }
            else {
                status=3;//省际
            }
            Carriage carriage =carriageService.findByStatus(status);
            cargoinfo.setCarriage(carriage.getCost());
            cargoinfoService.updateById(cargoinfo);
            return RestResult.ok().data("carriage",carriage.getCost());
        }

    }












}


