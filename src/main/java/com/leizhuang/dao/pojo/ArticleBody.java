package com.leizhuang.dao.pojo;

import lombok.Data;

/**
 * @author LeiZhuang
 * @date 2021/10/28 15:10
 */
@Data
public class ArticleBody {
    private Long id;
    private String content;
    private String contentHtml;
    private Long articleId;
}
