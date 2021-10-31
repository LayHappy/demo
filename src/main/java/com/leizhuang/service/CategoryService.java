package com.leizhuang.service;

import com.leizhuang.vo.CategoryVo;
import com.leizhuang.vo.Result;

/**
 * @author LeiZhuang
 * @date 2021/10/28 15:30
 */
public interface CategoryService {
    CategoryVo findCategoryById(Long categoryId);

    Result findAll();
}
