package com.leizhuang.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.leizhuang.dao.mapper.ArticleMapper;
import com.leizhuang.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author LeiZhuang
 * @date 2021/10/28 19:31
 */
@Service
public class ThreadService {
    //期望次操作在线程池中执行，不会影响原有的住线程
    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {

        int viewCounts = article.getViewCounts();

        Article articleUpdate = new Article();

        articleUpdate.setViewCounts(viewCounts + 1);

        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();

        updateWrapper.eq(Article::getId,article.getId());
//        设置一个 为了在多线程的环境下， 线程安全
        updateWrapper.eq(Article::getViewCounts,viewCounts);
//        update article set view_count=100 where view_count=99 and id =?
        articleMapper.update(articleUpdate, updateWrapper);

        /*try {

            Thread.sleep(5000);

            System.out.println("更新已经完成");

        } catch (InterruptedException e) {

            e.printStackTrace();
        }*/

    }
}
