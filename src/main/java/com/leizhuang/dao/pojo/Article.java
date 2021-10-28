package com.leizhuang.dao.pojo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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
    private Integer commentCounts;
    private Integer viewCounts;
    private Long authorId;//作者id
    private Long bodyId;//内容id
    private Long categoryId;//类别id
    private Integer weight;//置顶
    private Long createDate;//创建时间

}
