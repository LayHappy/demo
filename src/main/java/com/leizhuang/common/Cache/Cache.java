package com.leizhuang.common.Cache;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {
    long expire() default 1*60*1000;
//    缓存标识key
    String name() default "";
}
