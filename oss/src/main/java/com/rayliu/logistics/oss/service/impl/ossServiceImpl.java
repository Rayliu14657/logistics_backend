package com.rayliu.logistics.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.rayliu.logistics.oss.service.ossService;
import com.rayliu.logistics.oss.util.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class ossServiceImpl implements ossService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId=ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        try{
            OSS ossClient = new OSSClientBuilder().build(endPoint,accessKeyId,accessKeySecret);
            InputStream inputStream  = file.getInputStream();
            String fileName = file.getOriginalFilename();

            String uuid= UUID.randomUUID().toString().replaceAll("-","");
            fileName=uuid+fileName;
            String datePath=new DateTime().toString("yyyy/MM/dd");
            fileName=datePath+"/"+fileName;

            ossClient.putObject(bucketName,fileName,inputStream);
            ossClient.shutdown();
            String url= "https://"+bucketName+"."+endPoint+"/"+fileName;
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
