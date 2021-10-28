package com.leizhuang.service;

import com.leizhuang.dao.pojo.SysUser;
import com.leizhuang.vo.Result;

public interface SysUserService {
    SysUser findUserById(Long id);

    SysUser findUser(String account, String password);

    Result findUserByToken(String token);
//根据账户查找用户
    SysUser findUserByAccount(String account);
//注册用户
    void save(SysUser sysUser);
}
