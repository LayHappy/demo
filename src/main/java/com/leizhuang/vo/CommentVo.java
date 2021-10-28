package com.leizhuang.vo;

import lombok.Data;

import java.util.List;

/**
 * @author LeiZhuang
 * @date 2021/10/28 20:25
 */
@Data
public class CommentVo {
    private Long id;
    private UserVo author;
    private String content;
    private List<CommentVo> childrens;
    private String createDate;
    private Integer level;
    private UserVo toUser;
}
