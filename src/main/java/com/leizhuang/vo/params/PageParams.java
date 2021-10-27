package com.leizhuang.vo.params;

import lombok.Data;

/**
 * @author LeiZhuang
 * @date 2021/10/25 16:06
 */
@Data
public class PageParams {
    private int page=1;
    private int pageSize=10;
}
