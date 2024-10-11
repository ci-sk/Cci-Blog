package org.example.controller;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import jakarta.annotation.Resource;
import org.example.entity.RestBean;
import org.example.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/find")
public class FileController {

    @Autowired
    FileService fileService;


    @PostMapping("/file/upload")
    public RestBean<?> upload(MultipartFile multipartFile) throws IOException {
        String upload = fileService.upload(multipartFile);
        if(upload==null) return RestBean.failure(401,"上传失败");
        return RestBean.success(upload);
    }


}


