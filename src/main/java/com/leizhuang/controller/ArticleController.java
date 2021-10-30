package com.leizhuang.controller;

import com.leizhuang.service.ArticleService;
import com.leizhuang.vo.Result;
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
    public static Connection conn;
    public static final String url = "jdbc:mysql://localhost:3306/sqzy?serverTimezone"
            + "=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";
    public static final String name = "com.mysql.cj.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "0000";

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
    @PostMapping("view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId){
        return articleService.findArticleById(articleId);
    }

    public String test(String sid){
        String arr[]={"1","2","3","4","5"};
        int[] array = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();

        for (int i : array) {
            i=i*2;
            System.out.print(i );
        }
      String string=String.valueOf(arr);;
        return string;
    }



    public Connection getConnection() {  //建立返回值为Connection的方法
        try {        //加载数据库驱动类
            Class.forName(name);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            conn= DriverManager.getConnection(url,user,password);//通过访问数据库的URL获取数据库连接对象
            System.out.println(conn);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return conn;//按方法要求返回一个Connection对象
    }
    }


