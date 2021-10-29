package com.leizhuang.service;

import com.leizhuang.vo.Result;

public interface CommentsService {
    /**
     * 根据 文章id查询所有的评论列表
     * @param id
     * @return
     */
     Result commentsByArticleId(Long id);
}
