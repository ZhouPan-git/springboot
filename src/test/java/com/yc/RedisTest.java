package com.yc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author zp
 * @Date 2023/8/17 9:39
 * @PackageName:com.yc
 * @ClassName: RedisTest
 * @Description:
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={AppMain.class})
@Slf4j
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void contextLoads(){
        redisTemplate.opsForValue().set("name","zp");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }
}