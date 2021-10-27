package com.leizhuang.service.impl;

import com.leizhuang.dao.mapper.SysUserMapper;
import com.leizhuang.dao.pojo.SysUser;
import com.leizhuang.service.SysUserService;
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
