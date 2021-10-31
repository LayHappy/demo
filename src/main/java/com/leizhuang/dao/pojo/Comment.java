package com.leizhuang.dao.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author LeiZhuang
 * @date 2021/10/28 20:07
 */
@Data
public class Comment {
//    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String content;
    private Long createDate;
    private Long articleId;
    private Long authorId;
    private Long parentId;
    private Long toUid;
    private Integer level;
}
