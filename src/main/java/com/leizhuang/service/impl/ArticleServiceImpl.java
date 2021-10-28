package com.leizhuang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.leizhuang.dao.dos.Archives;
import com.leizhuang.dao.mapper.ArticleBodyMapper;
import com.leizhuang.dao.mapper.ArticleMapper;
import com.leizhuang.dao.pojo.Article;
import com.leizhuang.dao.pojo.ArticleBody;
import com.leizhuang.service.ArticleService;
import com.leizhuang.service.CategoryService;
import com.leizhuang.service.SysUserService;
import com.leizhuang.service.TagService;
import com.leizhuang.vo.ArticleBodyVo;
import com.leizhuang.vo.ArticleVo;
import com.leizhuang.vo.Result;
import com.leizhuang.vo.params.PageParams;
import com.sun.org.apache.regexp.internal.RE;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LeiZhuang
 * @date 2021/10/25 16:15
 */
@Service
//@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public Result findArticleById(Long articleId) {
        /**
         * 1.根据id查询文章信息
         * 2.根据bodyId和categoryId 去做关联查询
         */

        Article article=this.articleMapper.selectById(articleId);

        ArticleVo articleVo = copy(article, true, true,true,true);

        return Result.success(articleVo);
    }

    @Override
    public Result listArchives() {
        List<Archives> archivesList=articleMapper.listArchives();
        return Result.success(archivesList);
    }

    @Override
    public Result listArticle(PageParams pageParams) {
        /**
         * 分页插叙你article数据库表
         */
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
//        是否置顶进行排序

        //order by create_data desc
        queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);

        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);

        List<Article> records = articlePage.getRecords();

        //不能直接返回
        List<ArticleVo> articleVoList = copyList(records, true, true);

        return Result.success(articleVoList);

    }
//最热文章
    @Override
    public Result hotArticle(int limit) {

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.orderByDesc(Article::getViewCounts);

        queryWrapper.select(Article::getId, Article::getTitle);

        queryWrapper.last("limit "+limit);

//        select id,title from article order by view_counts desc limit 5

        List<Article> articles = articleMapper.selectList(queryWrapper);
        return Result.success(copyList(articles,false,false));
    }

    @Override
    public Result NewArticles(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.orderByDesc(Article::getCreateDate);

        queryWrapper.select(Article::getId, Article::getTitle);

        queryWrapper.last("limit "+limit);

//        select id,title from article order by create_date desc limit 5

        List<Article> articles = articleMapper.selectList(queryWrapper);
        return Result.success(copyList(articles,false,false));
    }

    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor) {

        List<ArticleVo> articleVoList = new ArrayList<>();

        for (Article record : records) {

            articleVoList.add(copy(record, isTag, isAuthor,false,false));
        }

        return articleVoList;
    }
    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor,boolean idBody,boolean isCategory) {

        List<ArticleVo> articleVoList = new ArrayList<>();

        for (Article record : records) {

            articleVoList.add(copy(record, isTag, isAuthor,idBody,isCategory));
        }

        return articleVoList;
    }
    @Autowired
    private CategoryService categoryService;


    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor,boolean idBody,boolean isCategory) {

        ArticleVo articleVo = new ArticleVo();

        BeanUtils.copyProperties(article, articleVo);

        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
//并不是所有的接口，都需要标签，作者信息
        if (isTag) {
            Long articleId = article.getId();
            articleVo.setTags(tagService.findTagsByArticleId(articleId));
        }

        if (isAuthor) {
            Long authorId = article.getAuthorId();
            articleVo.setAuthor(sysUserService.findUserById(authorId).getNickname());
        }
        if (idBody){
            Long bodyId=article.getBodyId();
            articleVo.setBody(findArticleBodyById(bodyId));
        }
        if (isCategory){
            Long categoryId=article.getCategoryId();
            articleVo.setCategory(categoryService.findCategoryById(categoryId));
        }
        return articleVo;
    }
@Autowired
    private ArticleBodyMapper articleBodyMapper;
    private ArticleBodyVo findArticleBodyById(Long bodyId) {
        ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
        ArticleBodyVo articleBodyVo =new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());

        return articleBodyVo;
    }
}