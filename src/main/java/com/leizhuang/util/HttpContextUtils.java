package com.leizhuang.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LeiZhuang
 * @date 2021/10/31 10:01
 */
public class HttpContextUtils {
    public static HttpServletRequest getHttpServletRequest(){
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
