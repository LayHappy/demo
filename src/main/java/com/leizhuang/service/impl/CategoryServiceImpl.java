package com.leizhuang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leizhuang.dao.mapper.CategoryMapper;
import com.leizhuang.dao.pojo.Category;
import com.leizhuang.service.CategoryService;
import com.leizhuang.vo.CategoryVo;
import com.leizhuang.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LeiZhuang
 * @date 2021/10/28 15:30
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Result categoryDetailById(Long id) {
        Category category = categoryMapper.selectById(id);

        return Result.success(copy(category));
    }

    @Override
    public Result findAllDetail() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();


        List<Category> categories = categoryMapper.selectList(queryWrapper);

//       页面交互的对象

        return Result.success(copyList(categories));
    }

    @Override
    public CategoryVo findCategoryById(Long categoryId) {
        Category category = categoryMapper.selectById(categoryId);
        CategoryVo categoryVo = new CategoryVo();
//        if (category != null) {
        BeanUtils.copyProperties(category, categoryVo);
//        }

        return categoryVo;
    }

    @Override
    public Result findAll() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Category::getId, Category::getCategoryName);


        List<Category> categories = categoryMapper.selectList(new LambdaQueryWrapper<>());


//       页面交互的对象

        return Result.success(copyList(categories));
    }

    private List<CategoryVo> copyList(List<Category> categoryList) {
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryVoList.add(copy(category));
        }
        return categoryVoList;
    }

    private CategoryVo copy(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }
}
