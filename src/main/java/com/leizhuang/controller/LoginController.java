package com.leizhuang.controller;

import com.leizhuang.service.LoginService;
import com.leizhuang.vo.Result;
import com.leizhuang.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author LeiZhuang
 * @date 2021/10/27 21:29
 */
@RestController
@RequestMapping("login")
public class LoginController {
     @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody  LoginParam loginParam){
//    登陆 验证用户，访问用户表，但是
return  loginService.login(loginParam);

    }
}
