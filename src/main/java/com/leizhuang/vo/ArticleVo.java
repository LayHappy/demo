package com.leizhuang.vo;

import lombok.Data;

import java.util.List;

/**
 * @author LeiZhuang
 * @date 2021/10/25 16:25
 */
@Data
public class ArticleVo {
    private Long id;
    private String title;
    private String summary;
    private int commentCounts;
    private int viewCounts;
    private int weight;
    private String createDate;
    private String author;
//   private ArticleBodyVo body;
    private List<TagVo> tags;
//    private List<CategoryVo> categorys;

}
