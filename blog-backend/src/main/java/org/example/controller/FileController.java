package org.example.controller;

import org.example.entity.RestBean;
import org.example.service.FileService;
import org.example.service.impl.AliOssServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    FileService fileService;

    @Autowired
    private AliOssServiceImpl aliOSSUtils;


    @ResponseBody
    @RequestMapping("/file/qi/upload")
    public RestBean<?> upload(MultipartFile multipartFile) throws IOException {
//        response.setContentType("application/json;charset=utf-8");
        System.out.println("@@@");
        String upload = fileService.upload(multipartFile);
        if(upload==null) return RestBean.failure(401,"上传失败");
        return RestBean.success(upload);
    }

    @PostMapping("/file/al/upload")
    //  MultipartFile image
    public RestBean<?> uploadAl(MultipartFile multipartFile) throws IOException {
        //调用阿里云OSS工具类，将上传上来的文件存入阿里云
        String url = aliOSSUtils.upload(multipartFile);
        //将图片上传完成后的url返回，用于浏览器回显展示
        return RestBean.success(url);
    }
}


