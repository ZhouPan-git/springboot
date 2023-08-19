package com.yc.config;

import com.yc.bean.OpRecord;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;

/**
 * @Author zp
 * @Date 2023/8/10 19:02
 * @PackageName:com.yc.config
 * @ClassName: Configs
 * @Description:
 * @Version 1.0
 */
@Configuration
@ComponentScan(basePackages = "com.yc")
@MapperScan("com.yc.mappers")
public class Configs {
}