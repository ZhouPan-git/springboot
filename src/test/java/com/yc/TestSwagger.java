package com.yc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author zp
 * @Date 2023/8/15 14:49
 * @PackageName:com.yc
 * @ClassName: Swagger
 * @Description:
 * @Version 1.0
 */
@SpringBootApplication
@EnableSwagger2
//@MapperScan("com.yc.mappers")
public class TestSwagger {
    public static void main(String[] args) {
        SpringApplication.run(TestSwagger.class,args);
    }
}