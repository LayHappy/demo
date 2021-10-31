package com.leizhuang.controller;

import com.leizhuang.common.Cache.Cache;
import com.leizhuang.common.aop.LogAnnotation;
import com.leizhuang.service.ArticleService;
import com.leizhuang.vo.Result;
import com.leizhuang.vo.params.ArticleParam;
import com.leizhuang.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



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
    @Cache(expire = 5 * 60 * 1000,name = "listArticle")
    public Result listArticle(@RequestBody PageParams pageParams) {

        return articleService.listArticle(pageParams);
    }

    /**
     * 首页 最热文章
     *
     * @return
     */
    @PostMapping("hot")
    @Cache(expire = 5*60*1000,name="hot_article")
    public Result HotArticle() {
        int limit = 5;
        return articleService.hotArticle(limit);
    }

    /**
     * 首页 最新文章
     * @return
     */
    @PostMapping("new")
    @Cache(expire = 5*60*1000,name="news_article")
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


