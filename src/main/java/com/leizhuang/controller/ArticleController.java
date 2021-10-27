package com.leizhuang.controller;

import com.leizhuang.service.ArticleService;
import com.leizhuang.vo.Result;
import com.leizhuang.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        int limit = 5;

        return articleService.listArchives();
    }
}
