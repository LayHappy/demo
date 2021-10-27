package com.leizhuang.dao.pojo;

import lombok.Data;

/**
 * @author LeiZhuang
 * @date 2021/10/25 15:53
 */
@Data
public class Article {
    public static final int Article_TOP = 1;
    public static final int Article_Common = 0;
    private Long id;
    private String title;
    private String summary;
    private int commentCounts;
    private int viewCounts;
    private Long authorId;//作者id
    private Long bodyId;//内容id
    private Long categoryId;//类别id
    private int weight = Article_Common;//置顶
    private Long createDate;//创建时间

}
