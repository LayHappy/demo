package com.leizhuang.common;

import com.alibaba.fastjson.JSON;
import com.leizhuang.common.aop.LogAnnotation;
import com.leizhuang.util.HttpContextUtils;
import com.leizhuang.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author LeiZhuang
 * @date 2021/10/31 9:47
 */
@Component
@Aspect//切面，定义了通知和切点的关系
@Slf4j//aop，定义一个切面，切面定义了切点和通知的关系
public class LogAspect {

    @Pointcut("@annotation(com.leizhuang.common.aop.LogAnnotation)")
    public void pt() {
    }

    @Around("pt()")//环绕通知
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long time = System.currentTimeMillis() - beginTime;
        recordLog(joinPoint, time);
        return result;
    }

    private void recordLog(ProceedingJoinPoint joinPoint, long time) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
        log.info("================ log start =================");
        log.info("module:{}", annotation.module());
        log.info("operator:{}", annotation.operator());

//        请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method:{}", className + "." + methodName + "()");

//                请求的参数
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args[0]);
        log.info("params:{}",params);

//        获取request 设置ip地址
        HttpServletRequest request= HttpContextUtils.getHttpServletRequest();
        log.info("ip:{}", IpUtils.getIpAddr(request));

        log.info("excute time:{}ms",time);
        log.info("================log end==============");
    }

}
