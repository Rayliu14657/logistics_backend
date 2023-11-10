package com.rayliu.logistics.controller;



import com.rayliu.logistics.model.Admin;
import com.rayliu.logistics.service.AdminService;
import com.rayliu.common.result.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-17
 */
@Api(description = "管理员信息管理模块")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //管理员增加控制器
    @ApiOperation(value = "管理员的增加")
    @PostMapping("/add")
    public RestResult add(@RequestBody Admin admin){
        Admin flag = adminService.findByAdminID(admin.getAdminID());
        if(flag==null){
            adminService.save(admin);
            return RestResult.ok();
        }
        else {
            return RestResult.error();
        }
    }

    //管理员删除控制器
    @ApiOperation(value = "管理员的删除")
    @DeleteMapping("{id}")
    public RestResult delete(
            @ApiParam(name ="id",value = "管理员id",required = true)
            @PathVariable int id
    ){
        boolean flag = adminService.removeById(id);
        if(flag){
            return RestResult.ok();
        }
        else {
            return RestResult.error();
        }
    }

    //管理员修改控制器
    @ApiOperation(value = "管理员的更新")
    @PostMapping("/update")
    public RestResult update(@RequestBody Admin admin){
        boolean flag = adminService.updateById(admin);
        if(flag){
            return RestResult.ok();
        }
        else {
            return RestResult.error();
        }
    }

    //管理员依照ID查询控制器
    @ApiOperation(value = "管理员依ID查询")
    @GetMapping("getById/{id}")
    public RestResult getById(
            @ApiParam(name = "id", value = "管理员ID", required = true)
            @PathVariable int id) {
        Admin flag = adminService.findByAdminID(id);
        if (flag != null)
            return RestResult.ok().data("admin", flag);
        else
            return RestResult.error();
    }

    //分页查询所有管理员控制器
    @ApiOperation(value = "员工的分页查询")
    @PostMapping("/page/{current}/{limit}")
    public RestResult findPage(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable int current,
            @ApiParam(name = "limit", value = "每页的记录数", required = true)
            @PathVariable int limit,
            @RequestBody(required = false) Admin admin) {
        System.out.println("查询条件：" + admin);
        return adminService.findPage(current, limit);
    }


    @ApiOperation(value = "管理员的登录")
    @PostMapping("/login")
    public Object login(@RequestBody Admin admin)
    {
        Admin login = adminService.login(admin);

        if(login!=null)
        {
            return RestResult.ok().data("admin",login);
        }
        else
        {
            return RestResult.error();
        }
    }







}

