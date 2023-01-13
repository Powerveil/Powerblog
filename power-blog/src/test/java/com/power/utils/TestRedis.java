package com.power.utils;

import com.power.domain.entity.LoginUser;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author power
 * @Date 2023/1/13 16:33
 */
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.power"})
@SpringBootTest(classes = TestRedis.class)
public class TestRedis {

    @Autowired
    private RedisCache redisCache;

    @Test
    public void testRedis01() {
        System.out.println(redisCache);
        String key = "bloglogin:" + 1;
        LoginUser loginUser = redisCache.getCacheObject(key);
        System.out.println(loginUser);
    }
}
