package com.leizhuang.service;

import com.leizhuang.vo.Result;
import com.leizhuang.vo.TagVo;

import java.util.List;

public interface TagService  {
    List<TagVo> findTagsByArticleId(Long articleId);

    Result hots(int limit);

    /**
     * 查询所有的文章标签
     * @return
     */
    Result findAll();
}
