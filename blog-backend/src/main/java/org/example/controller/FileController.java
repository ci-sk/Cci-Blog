package org.example.controller;

import org.example.entity.RestBean;
import org.example.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @ResponseBody
    @RequestMapping("/file/upload")
    public RestBean<?> upload(MultipartFile multipartFile) throws IOException {
        String upload = fileService.upload(multipartFile);
        if(upload==null) return RestBean.failure(401,"上传失败");
        return RestBean.success(upload);
    }


}


