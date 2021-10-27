package com.leizhuang.service.impl;

import com.leizhuang.dao.mapper.TagMapper;
import com.leizhuang.dao.pojo.Tag;
import com.leizhuang.service.TagService;
import com.leizhuang.vo.Result;
import com.leizhuang.vo.TagVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author LeiZhuang
 * @date 2021/10/26 22:22
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;


    public TagVo copy(Tag tag){
        TagVo tagvo=new TagVo();
        BeanUtils.copyProperties(tag,tagvo);
        return tagvo;
    }
    public List<TagVo> copyList(List<Tag> tagList){
         List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }



    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
//mybatisplus无法进行多表查询
      List<Tag> tags= tagMapper.findTagsByArticleId(articleId);
        return copyList(tags);
    }
    @Override
    public Result hots(int limit) {
/**
 * 标签锁拥有的文章数量最多就是最热标签
 * 2.查询的时候根据tag_id 分组 计数，从大到小排列，取前limit个
 */
List<Long> tagIds=tagMapper.findHotsTagIds(limit);
if (CollectionUtils.isEmpty(tagIds)){
    return Result.success(Collections.emptyList());
}
//需求的是tagId和tagName  Tag对象
        //select * from tag where id in (1,2,3,4,5)
        List<Tag> tagList=tagMapper.findTagsByTagIds(tagIds);
        return Result.success(tagList);
    }
}
