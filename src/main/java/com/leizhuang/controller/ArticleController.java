package com.leizhuang.controller;

import com.leizhuang.common.aop.LogAnnotation;
import com.leizhuang.service.ArticleService;
import com.leizhuang.vo.Result;
import com.leizhuang.vo.params.ArticleParam;
import com.leizhuang.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author LeiZhuang
 * @date 2021/10/25 16:04
 */
//json数据进行交互
@RestController
@RequestMapping("articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //    首页文章列表
    @PostMapping
    @LogAnnotation(module = "文章",operator = "获取文章列表")
    public Result listArticle(@RequestBody PageParams pageParams) {

        return articleService.listArticle(pageParams);
    }

    /**
     * 首页 最热文章
     *
     * @return
     */
    @PostMapping("hot")
    public Result HotArticle() {
        int limit = 5;

        return articleService.hotArticle(limit);
    }

    /**
     * 首页 最新文章
     * @return
     */
    @PostMapping("new")
    public Result NewArticles() {
        int limit = 5;

        return articleService.NewArticles(limit);
    }

    @PostMapping("listArchives")
    public Result listArchives() {
        return articleService.listArchives();
    }


    @PostMapping("view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId){
        return articleService.findArticleById(articleId);
    }

@PostMapping("publish")
    public Result publish(@RequestBody ArticleParam articleParam){
        return articleService.publish(articleParam);
}




    }


