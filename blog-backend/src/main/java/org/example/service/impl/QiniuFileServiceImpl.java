package org.example.service.impl;

import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import jakarta.annotation.Resource;
import org.example.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 七牛云对象存储文件服务
 */
@Service("fileService")
public class QiniuFileServiceImpl implements FileService {

    @Value("${qiniu.accessKey}")
    String accessKey;

    @Value("${qiniu.secretKey}")
    String secretKey;

    @Value("${qiniu.bucket}")
    String bucket;

    @Value("${qiniu.domain}")
    String domain;


    @Value("${qiniu.directory}")
    String directory;


    @Override
    public String upload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("文件是空的");
        }

        // 创建上传token
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        // 设置上传配置，Region要与存储空间所属的存储区域保持一致
        Configuration cfg = new Configuration(Region.xinjiapo());

        // 创建上传管理器
        UploadManager uploadManager = new UploadManager(cfg);

        String originalFilename = file.getOriginalFilename();
        // 构造文件目录和文件名
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileKey = directory + UUID.randomUUID() + suffix;

        // 上传文件
        Response response = uploadManager.put(file.getInputStream(), fileKey, upToken, null, null);

        // 返回文件url
        return  "http://"+domain +"/"+ fileKey;
    }
}

