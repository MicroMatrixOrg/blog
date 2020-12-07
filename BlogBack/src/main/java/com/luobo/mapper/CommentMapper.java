package com.luobo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.common.dto.CommentDto;
import com.luobo.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author David
 * @since 2020-12-03
 */
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * @title getCommentList
     * @description  根据文章ID找到评论列表，并分页
     * @author davidmorgan
     * @param: page
     * @param: queryWrapper
     * @updateTime 2020/12/4 09:43
     * @return: java.util.List<com.luobo.entity.Comment>
     */
    List<Comment> getCommentList(Page<Comment> page, @Param(Constants.WRAPPER) QueryWrapper<Comment> queryWrapper, CommentDto comment);

    /**
     * @title getSonCommentList
     * @description 查询评论的子评论
     * @author davidmorgan
     * @param: page
     * @param: queryWrapper
     * @param: comment
     * @updateTime 2020/12/4 16:38
     * @return: java.util.List<com.luobo.entity.Comment>
     */
    List<Comment> getSonCommentList(Page<Comment> page, @Param(Constants.WRAPPER) QueryWrapper<Comment> queryWrapper, CommentDto comment);

    /**
     * @title getCountByParentId
     * @description 获取该评论下的回复数量
     * @author davidmorgan
     * @param: comment
     * @updateTime 2020/12/5 11:25
     * @return: java.lang.Integer
     */
    Integer getCountByParentId(CommentDto comment);
}
