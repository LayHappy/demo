package com.leizhuang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leizhuang.dao.mapper.SysUserMapper;
import com.leizhuang.dao.pojo.SysUser;
import com.leizhuang.service.LoginService;
import com.leizhuang.service.SysUserService;
import com.leizhuang.vo.ErrorCode;
import com.leizhuang.vo.LoginUserVo;
import com.leizhuang.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LeiZhuang
 * @date 2021/10/26 22:44
 */
@Service
public class SysUserServiceImpl implements SysUserService {
@Autowired
private SysUserMapper sysUserMapper;
@Autowired
private LoginService loginService;
//登陆需要findUser
    @Override
    public SysUser findUser(String account, String password) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.eq(SysUser::getPassword,password);
        queryWrapper.select(SysUser::getAccount,SysUser::getId,SysUser::getNickname);
        queryWrapper.last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public Result findUserByToken(String token) {
        /**
         * 1.token合法性校验，
         *   1.1是否为空，解析是否成功，
         *   1.2redis是否存在
         * 2.如果校验失败，返回错误，
         * 3.如果成功，返回对应的结果，LoginUserVo
         *
         */

        SysUser sysUser=loginService.checkToken(token);

        if (sysUser == null) {

            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }
        LoginUserVo loginUserVo=new LoginUserVo();
        loginUserVo.setId(sysUser.getId());
        loginUserVo.setNickname(sysUser.getNickname());
        loginUserVo.setAvatar(sysUser.getAvatar());
        loginUserVo.setAccount(sysUser.getAccount());

        return Result.success(loginUserVo);
    }

    @Override
    public SysUser findUserById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);

        if (sysUser==null){
            sysUser=new SysUser();
            sysUser.setNickname("牛逼666");
        }
        return sysUser;
    }
}
