package com.leizhuang.controller;

import com.leizhuang.dao.pojo.SysUser;
import com.leizhuang.service.impl.LoginServiceImpl;
import com.leizhuang.util.UserThreadLocal;
import com.leizhuang.vo.Result;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LeiZhuang
 * @date 2021/10/28 13:22
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private static final String salt = "niubi666";
    @RequestMapping
    public Result test(){
//        sysUser
        SysUser sysUser= UserThreadLocal.get();
        return Result.success(null);
    }
      public static void main(String[] args) {
          LoginServiceImpl loginService = new LoginServiceImpl();

          String s = DigestUtils.md5Hex(21 + salt);
          System.out.println(s);
      }
}
