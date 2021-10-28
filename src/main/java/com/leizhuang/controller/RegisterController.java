package com.leizhuang.controller;

import com.leizhuang.service.LoginService;
import com.leizhuang.vo.Result;
import com.leizhuang.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LeiZhuang
 * @date 2021/10/28 10:37
 */
@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result register(@RequestBody LoginParam loginParam) {
//        sso 单点登陆，后期如果把登陆注册功能 提出去(单独的服务，可以独立提供接口服务)
        return loginService.register(loginParam);
    }
}
