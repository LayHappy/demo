package com.leizhuang.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leizhuang.dao.mapper.CommentMapper;
import com.leizhuang.dao.pojo.Comment;
import com.leizhuang.service.CommentsService;
import com.leizhuang.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LeiZhuang
 * @date 2021/10/28 20:10
 */
@RestController
@RequestMapping("comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @GetMapping("article/{id}")
    public Result comments(@PathVariable("id") Long id) {

        
        return commentsService.commentsByArticleId(id);
    }
}
