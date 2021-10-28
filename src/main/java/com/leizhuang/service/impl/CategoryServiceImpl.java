package com.leizhuang.service.impl;

import com.leizhuang.dao.mapper.CategoryMapper;
import com.leizhuang.dao.pojo.Category;
import com.leizhuang.service.CategoryService;
import com.leizhuang.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LeiZhuang
 * @date 2021/10/28 15:30
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryVo findCategoryById(Long categoryId) {
        Category category = categoryMapper.selectById(categoryId);
        CategoryVo categoryVo =new CategoryVo();
//        if (category != null) {
            BeanUtils.copyProperties(category,categoryVo);
//        }

        return categoryVo;
    }
}
