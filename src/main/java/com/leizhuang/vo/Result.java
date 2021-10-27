package com.leizhuang.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author LeiZhuang
 * @date 2021/10/25 16:07
 */
@Data
@AllArgsConstructor
public class Result {
    private boolean success;

    private int code;

    private String msg;

    private Object data;

    public static Result success(Object data){
        return new Result(true,200,"success",data);
    }
    public static Result fail(int code,String msg){
        return new Result(false,code,msg,null);
    }
}
