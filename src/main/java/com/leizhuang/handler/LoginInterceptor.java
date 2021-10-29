package com.leizhuang.handler;

import com.alibaba.fastjson.JSON;
import com.leizhuang.dao.pojo.SysUser;
import com.leizhuang.service.LoginService;
import com.leizhuang.util.UserThreadLocal;
import com.leizhuang.vo.ErrorCode;
import com.leizhuang.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


/**
 * @author LeiZhuang
 * @date 2021/10/28 13:00
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginService loginService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        在执行controller方法(handler)之前进行执行，

        /**
         * 1.需要判断 请求的接口路径是否为handlerMethod(Controller)
         * 2.判断token是否为空，如果为空 未登录
         * 3.如果token不为空，登陆验证，loginService checkToken
         * 4.如果认证成功，放行
         */

        if (!(handler instanceof HandlerMethod)) {
//            handler可能是 RequestResourceHandler springboot程序访问静态资源 默认去classpath下的static目录去查询
            return true;
        }
        String token = request.getHeader("Authorization");

        log.info("======================request start=======================");
        String requestURI=request.getRequestURI();
        log.info("request uri:{}",requestURI);
        log.info("request method:{}",request.getMethod());
        log.info("token :{}",token);
        log.info("=====================request end====================");

        if (StringUtils.isBlank(token)) {
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null) {
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
//        登陆验证成功，放行
//        我希望在controller中，直接获取用户的信息，怎么做
        UserThreadLocal.put(sysUser);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        如果不删除ThreadLocal中用完的信息，会有内存泄漏的风险
        UserThreadLocal.remove();

    }
}
