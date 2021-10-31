package com.leizhuang.controller;

import com.leizhuang.service.CategoryService;
import com.leizhuang.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LeiZhuang
 * @date 2021/10/31 8:47
 */
@RestController
@RequestMapping("categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Result categories() {
        return categoryService.findAll();
    }

    @GetMapping("detail")
    public Result categoriesDetail(){
        return categoryService.findAllDetail();
    }

    @GetMapping("detail/{id}")
    public Result categoryDetailById(@PathVariable("id") Long id){
        return categoryService.categoryDetailById( id);
    }
}
