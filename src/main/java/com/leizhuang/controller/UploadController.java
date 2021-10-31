package com.leizhuang.controller;

import com.leizhuang.util.QiniuUtils;
import com.leizhuang.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author LeiZhuang
 * @date 2021/10/31 13:57
 */
@RestController
@RequestMapping("upload")
public class UploadController {
@Autowired
private QiniuUtils qiniuUtils;
    @PostMapping
    public Result upload(@RequestParam("image")MultipartFile file){
//        原始文件名称
        String originalFilename = file.getOriginalFilename();
//        唯一的文件名称
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
//        上传文件，上传到哪个地方 q七牛云，云服务，按量付费，速度快，把图片发送到离用户最近的服务器上，
//        降低自身应用服务器的贷款消耗
        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload){
            return Result.success(QiniuUtils.url+"/"+fileName);
        }
        return Result.fail(200001,"上传失败");


    }
}
