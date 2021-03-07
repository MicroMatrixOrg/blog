package com.luobo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.common.dto.CommentDto;
import com.luobo.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author David
 * @since 2020-12-03
 */
public interface CommentService extends IService<Comment> {
    /**
     * @title getCommentList
     * @description 获取评论列表
     * @author davidmorgan
     * @param: page
     * @param: queryWrapper
     * @updateTime 2020/12/3 15:54
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.luobo.entity.Comment>
     */
    Page<Comment> getCommentList(Page<Comment> page, QueryWrapper<Comment> queryWrapper, CommentDto comment);

    /**
     * @title getSonCommentList
     * @description 获取评论下的子评论
     * @author davidmorgan
     * @param: page
     * @param: queryWrapper
     * @param: comment
     * @updateTime 2020/12/4 16:40
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.luobo.entity.Comment>
     */
    Page<Comment> getSonCommentList(Page<Comment> page, QueryWrapper<Comment> queryWrapper, CommentDto comment);

    /**
     * @title getCountByParentId
     * @description 获取该评论下的回复数量
     * @author davidmorgan
     * @param: commentDto
     * @updateTime 2020/12/5 11:26
     * @return: java.lang.Integer
     */
    Integer getCountByParentId(CommentDto commentDto);

    /**
     * @title deleteComment
     * @description 删除改文章下的评论
     * @author davidmorgan
     * @param: blogId
     * @updateTime 2021/3/7 13:00
     * @return: java.lang.Integer
     */
    Integer deleteComment(Long blogId);
}
