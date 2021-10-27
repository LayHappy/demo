package com.leizhuang.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leizhuang.dao.pojo.Article;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleMapper extends BaseMapper<Article> {

}
