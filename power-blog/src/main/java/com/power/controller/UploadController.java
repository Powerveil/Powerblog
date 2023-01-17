package com.power.controller;

import com.power.domain.ResponseResult;
import com.power.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author power
 * @Date 2023/1/17 15:24
 */
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    //TODO 前端没有要求传token 但是这个是要传token的 找人改前端代码
    public ResponseResult uploadImg(MultipartFile img) {
        return uploadService.uploadImg(img);
    }
}
