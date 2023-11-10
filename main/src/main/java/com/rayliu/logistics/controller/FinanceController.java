package com.rayliu.logistics.controller;

import com.rayliu.logistics.model.Cargoinfo;
import com.rayliu.logistics.model.Finance;
import com.rayliu.logistics.service.CargoinfoService;
import com.rayliu.logistics.service.FinanceService;
import com.rayliu.common.result.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 财务表 前端控制器
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-18
 */
@Api(description = "财务信息管理模块")
@RestController
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    FinanceService financeService;

    @Autowired
    CargoinfoService cargoinfoService;





    @ApiOperation(value = "增加一个货票")
    @PostMapping("/add")
    public RestResult add(@RequestBody Finance finance){
        Finance flag=financeService.findId(finance.getId());
        if(flag==null){
            financeService.save(finance);
            return RestResult.ok();
        }
        else return RestResult.error();
    }

    @ApiOperation(value = "删除货票信息")
    @DeleteMapping("/delete/{cargoID}")
    public RestResult delete(
            @ApiParam(name="cargoID",value = "货票号",required = true)
            @PathVariable int cargoID)
    {
        Finance flag2=financeService.findId(cargoID);
        boolean flag=financeService.removeById(cargoID);
        if(flag&&flag2!=null)
            return RestResult.ok();
        else
            return RestResult.error();
    }

    @ApiOperation(value ="修改货票信息")
    @PostMapping("/update")
    public RestResult update(@RequestBody Finance finance){
        boolean flag=financeService.updateById(finance);
        if (flag)
            return RestResult.ok();
        else
            return RestResult.error();
    }

    @ApiOperation(value = "按cargoID查询货票信息")
    @GetMapping("/select/{id}")
    public RestResult select(
            @ApiParam(name="id",value = "货票号",required = true)
            @PathVariable int id) {
        Finance data=financeService.findId(id);
        if(data!=null)
            return RestResult.ok().data("finance",data);
        else return RestResult.error();
    }


    @ApiOperation(value = "货票分页查询")
    @PostMapping("/page/{current}/{limit}")
    public RestResult findPage(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable int current,
            @ApiParam(name = "limit", value = "每页的记录数", required = true)
            @PathVariable int limit) {
        return financeService.findAllPage(current,limit);
    }

    @ApiOperation(value = "计算货物总价值(估价)")
    @PostMapping("/count/{id}")
    public RestResult count(
            @ApiParam(name = "id",value = "货号",required = true)
            @PathVariable int id){
        Cargoinfo cargoinfo = cargoinfoService.getById(id);
        double sumPrice= cargoinfo.getDueNum()*cargoinfo.getCost();
        Finance finance = new Finance();
        finance.setId(id);
        finance.setSum(sumPrice);
        boolean flag = financeService.updateById(finance);
        if(flag){
            return RestResult.ok().data("sum",sumPrice);
        }
        else {
            return RestResult.error();
        }
    }

    @ApiOperation(value="未估价货票的分页查询")
    @GetMapping("/unvalue/{current}/{limit}")
    public RestResult findUnValuedCargo(
            @ApiParam(name="current",value = "当前页",required = true)
            @PathVariable int current,
            @ApiParam(name = "limit",value = "每页的记录数",required = true)
            @PathVariable int limit){
        return financeService.findNullSum(current,limit);
    }

    @ApiOperation(value = "计算货物赔偿价值")
    @PostMapping("/compensate/{id}")
    public RestResult compensate(
            @ApiParam(name = "id",value = "货号",required = true)
            @PathVariable int id){
        Cargoinfo cargoinfo = cargoinfoService.getById(id);
        double comPrice= (cargoinfo.getDueNum()-cargoinfo.getActNum())*cargoinfo.getCost();
        Finance finance = new Finance();
        finance.setId(id);
        finance.setCompensate(comPrice);
        boolean flag = financeService.updateById(finance);
        if(flag){
            return RestResult.ok().data("sum",comPrice);
        }
        else {
            return RestResult.error();
        }
    }










}

