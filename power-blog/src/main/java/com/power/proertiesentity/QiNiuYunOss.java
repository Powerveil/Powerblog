package com.power.proertiesentity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author power
 * @Date 2023/1/17 15:31
 */
//@ConfigurationProperties(prefix = "oss")
@Component
public class QiNiuYunOss {
    String accessKey;
    String secretKey;
    String bucket;
}
