package com.leizhuang.vo.params;

import com.leizhuang.vo.CategoryVo;
import com.leizhuang.vo.TagVo;
import lombok.Data;

import java.util.List;

/**
 * @author LeiZhuang
 * @date 2021/10/31 9:06
 */@Data
public class ArticleParam {
     private Long id;
    private ArticleBodyParam body;
    private CategoryVo category;
    private String summary;
    private List<TagVo> tags;
    private String title;
}
