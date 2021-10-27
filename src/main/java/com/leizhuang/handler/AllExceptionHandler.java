package com.leizhuang.handler;

import com.leizhuang.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 对加了@Controller注解的方法进行拦截处理aop的实现
 *
 * @author LeiZhuang
 * @date 2021/10/27 12:50
 */
@ControllerAdvice
public class AllExceptionHandler {
//    进行异常处理，处理Exception.class的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回json数据
    public Result doException(Exception ex) {
        ex.printStackTrace();
        return  Result.fail(-99,"系统异常");
    }
}
