package com.power;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author power
 * @Date 2023/1/6 20:11
 */
@SpringBootApplication
@MapperScan("com.power.mapper")
@EnableScheduling
@EnableSwagger2
@EnableTransactionManagement
public class PowerBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(PowerBlogApplication.class, args);
    }
}
