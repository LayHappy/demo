package com.leizhuang.service;

import com.leizhuang.dao.pojo.SysUser;
import com.leizhuang.vo.Result;
import com.leizhuang.vo.UserVo;

public interface SysUserService {
    UserVo findUserVoById(Long id);
    SysUser findUserById(Long id);

    SysUser findUser(String account, String password);

    Result findUserByToken(String token);
//根据账户查找用户
    SysUser findUserByAccount(String account);
//注册用户
    void save(SysUser sysUser);

}
  /*
        ┌─────┐
        |  sysUserServiceImpl (field private com.leizhuang.service.LoginService com.leizhuang.service.impl.SysUserServiceImpl.loginService)
        ↑     ↓
        |  loginServiceImpl (field private com.leizhuang.service.SysUserService com.leizhuang.service.impl.LoginServiceImpl.sysUserService)
        └─────┘*/