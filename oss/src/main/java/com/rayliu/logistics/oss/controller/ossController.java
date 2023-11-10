package com.rayliu.logistics.oss.controller;

import com.rayliu.common.result.RestResult;
import com.rayliu.logistics.oss.service.ossService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Api(description = "阿里云文件管理")
@RestController
@RequestMapping("/oss/file")
@CrossOrigin
public class ossController {
    @Autowired
    private ossService ossService;

    @ApiOperation(value="文件上传")
    @PostMapping("upload")
    public RestResult uploadOssFile(MultipartFile multipartFile){
        String uploadUrl = ossService.uploadFileAvatar(multipartFile);
        return RestResult.ok().message("文件上传成功").data("url",uploadUrl);
    }

}
