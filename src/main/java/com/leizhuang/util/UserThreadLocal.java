package com.leizhuang.util;

import com.leizhuang.dao.pojo.SysUser;

/**
 * @author LeiZhuang
 * @date 2021/10/28 14:50
 */
public class UserThreadLocal {
    private UserThreadLocal() {
    }
//线程变量隔离
    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser) {
        LOCAL.set(sysUser);
    }

    public static SysUser get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }

}
