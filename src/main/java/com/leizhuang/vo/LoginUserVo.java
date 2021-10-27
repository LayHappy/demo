package com.leizhuang.vo;

import lombok.Data;

/**
 * @author LeiZhuang
 * @date 2021/10/27 22:27
 */
@Data
public class LoginUserVo {
    private Long id;
    private String account;
    private String nickname;
    private String avatar;
}
