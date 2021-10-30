package com.leizhuang.vo.params;

import lombok.Data;

/**
 * @author LeiZhuang
 * @date 2021/10/29 11:14
 */
@Data
public class CommentParam {
    private Long articleId;
    private String content;
    private Long parent;
    private Long toUserId;
}
