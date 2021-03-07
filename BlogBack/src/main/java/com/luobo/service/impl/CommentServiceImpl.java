package com.luobo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.common.dto.CommentDto;
import com.luobo.entity.Comment;
import com.luobo.mapper.CommentMapper;
import com.luobo.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author David
 * @since 2020-12-03
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    /**
     * @param page
     * @param queryWrapper
     * @title getCommentList
     * @description 获取评论列表
     * @author davidmorgan
     * @param: page
     * @param: queryWrapper
     * @updateTime 2020/12/3 15:54
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.luobo.entity.Comment>
     */
    @Override
    public Page<Comment> getCommentList(Page<Comment> page, QueryWrapper<Comment> queryWrapper, CommentDto comment) {
        return page.setRecords(commentMapper.getCommentList(page,queryWrapper,comment));
    }

    /**
     * @param page
     * @param queryWrapper
     * @param comment
     * @title getSonCommentList
     * @description 获取评论下的子评论
     * @author davidmorgan
     * @param: page
     * @param: queryWrapper
     * @param: comment
     * @updateTime 2020/12/4 16:40
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.luobo.entity.Comment>
     */
    @Override
    public Page<Comment> getSonCommentList(Page<Comment> page, QueryWrapper<Comment> queryWrapper, CommentDto comment) {
        return page.setRecords(commentMapper.getSonCommentList(page,queryWrapper,comment));
    }

    /**
     * @param commentDto
     * @title getCountByParentId
     * @description 获取该评论下的回复数量
     * @author davidmorgan
     * @param: commentDto
     * @updateTime 2020/12/5 11:26
     * @return: java.lang.Integer
     */
    @Override
    public Integer getCountByParentId(CommentDto commentDto) {
        return null;
    }

    /**
     * @title deleteComment
     * @description 删除改文章下的评论
     * @author davidmorgan
     * @param: blogId
     * @updateTime 2021/3/7 13:00
     * @return: java.lang.Integer
     */
    @Override
    public Integer deleteComment(Long blogId) {
        Map<String,Object> deleMap = new HashMap<>();
        deleMap.put("commentable_id",blogId);
        return commentMapper.deleteByMap(deleMap);
    }


}
