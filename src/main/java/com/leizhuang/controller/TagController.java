package com.leizhuang.controller;

import com.leizhuang.service.TagService;
import com.leizhuang.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LeiZhuang
 * @date 2021/10/27 10:50
 */
@RestController
@RequestMapping("tags")
public class TagController {
    @Autowired
    private TagService tagService;

    // /tags/hot
    @GetMapping("hot")
    public Result hot() {
        int limit = 6;
        return tagService.hots(limit);
    }
}
