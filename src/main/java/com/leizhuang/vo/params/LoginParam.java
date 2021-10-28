package com.leizhuang.vo.params;

import lombok.Data;

/**
 * @author LeiZhuang
 * @date 2021/10/27 21:32
 */
@Data
public class LoginParam {
  private  String account;
  private  String password;
  private String nickname;
}
