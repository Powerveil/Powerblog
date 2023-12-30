package com.power;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author power
 * @Date 2023/1/22 22:05
 */
@SpringBootApplication
@MapperScan("com.power.mapper")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableTransactionManagement
public class BlogAdminApplication {
    public static void main(String[] args) {
//        SpringApplication.run(BlogAdminApplication.class, args);
        ConfigurableApplicationContext run = SpringApplication.run(BlogAdminApplication.class, args);

        System.out.println("JVM启动成功");
    }
}
