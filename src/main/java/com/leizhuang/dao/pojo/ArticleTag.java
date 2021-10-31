package com.leizhuang.dao.pojo;

import lombok.Data;

/**
 * @author LeiZhuang
 * @date 2021/10/31 9:18
 */
@Data
public class ArticleTag {
    private Long id;

    private Long articleId;

    private Long tagId;

}
