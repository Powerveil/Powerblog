package com.power.service;

import com.power.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author power
 * @Date 2023/1/17 15:26
 */
public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
