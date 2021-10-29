package com.leizhuang.service;

import com.leizhuang.vo.CategoryVo;

/**
 * @author LeiZhuang
 * @date 2021/10/28 15:30
 */
public interface CategoryService {
    CategoryVo findCategoryById(Long categoryId);
}
