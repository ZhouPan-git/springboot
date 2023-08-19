package com.yc;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author zp
 * @Date 2023/8/10 19:02
 * @PackageName:com.yc
 * @ClassName: AppMain
 * @Description:
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.yc.mappers")
@EnableSwagger2   //启用swagger
@EnableScheduling  //启用定时器
@EnableCaching  //启用缓存
public class AppMain {
    public static void main(String[] args) {
        SpringApplication.run(AppMain.class,args);
    }
}