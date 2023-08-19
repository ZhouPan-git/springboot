package com.yc.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author zp
 * @Date 2023/8/16 19:31
 * @PackageName:com.yc.aspect
 * @ClassName: LoggingAspect
 * @Description:
 * @Version 1.0
 */
@Aspect
@Component
@Log4j2
public class RedisUpdateAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("execution(* com.yc.biz.AccountBizImpl.openAccount(..))")
    public void a(){}
    @Pointcut("execution(* com.yc.biz.AccountBizImpl.deposite(..))")
    public void b(){}
    @Pointcut("execution(* com.yc.biz.AccountBizImpl.withdraw(..))")
    public void c(){}
    @Pointcut("execution(* com.yc.biz.AccountBizImpl.transfer(..))")
    public void d(){}

    @AfterReturning("a()||b()||c()||d()")
    public void around(JoinPoint jp){
        log.info("当前执行的是："+jp.getSignature()+",清空缓存");
        Object result=null;
        Date d=new Date();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        redisTemplate.delete(df.format(d));
    }
}

