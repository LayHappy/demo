package com.leizhuang.service;

import com.leizhuang.dao.pojo.SysUser;
import com.leizhuang.vo.Result;
import com.leizhuang.vo.params.LoginParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author LeiZhuang
 * @date 2021/10/27 21:31
 */
@Transactional
public interface LoginService {
    /**
     * 登陆功能
     * @param loginParam
     * @return
     */
      Result login( LoginParam loginParam);

    SysUser checkToken(String token);
//退出登陆
    Result logout(String token);
//注册
    Result register(LoginParam loginParam);
}
