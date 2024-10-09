package org.example.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件操作服务
 */
public interface FileService {
    /**
     * 文件上传
     *
     * @param file 待上传的文件
     * @return 访问该文件的url
     * @throws IOException
     */
    String upload(MultipartFile file) throws IOException;
}
