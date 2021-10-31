package com.leizhuang.common.Cache;

import com.alibaba.fastjson.JSON;
import com.leizhuang.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * @author LeiZhuang
 * @date 2021/10/31 17:20
 */
@Component
@Aspect//切面，定义了通知和切点的关系
@Slf4j//aop，定义一个切面，切面定义了切点和通知的关系
public class CacheAspect {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Pointcut("@annotation(com.leizhuang.common.Cache.Cache)")
    public void pt() {
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) {
        try {
            Signature signature = pjp.getSignature();
//类名
            String className = pjp.getTarget().getClass().getSimpleName();
//调用的方法名
            String methodName = signature.getName();

            Class[] parameterTypes = new Class[pjp.getArgs().length];
            Object[] args = pjp.getArgs();
            String params = "";
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null) {
                    params += JSON.toJSONString(args[i]);
                    parameterTypes[i] = args[i].getClass();
                } else {
                    parameterTypes[i] = null;
                }
            }
            if (StringUtils.isNotEmpty(params)) {
//            加密，以防出现key过长以及字符串转义获取不到的情况
                params = DigestUtils.md5Hex(params);
            }
            Method method = pjp.getSignature().getDeclaringType().getMethod(methodName, parameterTypes);
            Cache annotation = method.getAnnotation(Cache.class);
            long expire = annotation.expire();
            String name = annotation.name();

            String redisKey = name + "::" + className + "::" + methodName + "::" + params;
            String redisValue = redisTemplate.opsForValue().get(redisKey);

            if (StringUtils.isNotEmpty(redisValue)) {
                log.info("走了缓存~~~,{},{}", className, methodName);
                Result result = JSON.parseObject(redisValue, Result.class);
                return result;
            }
            Object proceed = pjp.proceed();
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONString(proceed), Duration.ofMillis(expire));
            log.info("存入缓存~~~,{},{}", className, methodName);
            return proceed;

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return Result.fail(-999, "缓存系统错误");
    }
}
