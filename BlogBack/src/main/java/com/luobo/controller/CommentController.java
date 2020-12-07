package com.luobo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.common.dto.CommentDto;
import com.luobo.common.lang.Result;
import com.luobo.entity.Blog;
import com.luobo.entity.Comment;
import com.luobo.entity.User;
import com.luobo.service.BlogService;
import com.luobo.service.CommentService;
import com.luobo.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author David
 * @since 2020-11-25
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    BlogService blogService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    /**
     * @title review
     * @description 发布评论
     * @author davidmorgan
     * @param: comment
     * @updateTime 2020/12/3 13:54
     * @return: java.lang.Object
     */
    @RequiresAuthentication
    @PostMapping("/review")
    public Object review(@Validated  @RequestBody Comment comment){
        Blog temBlog = blogService.getById(comment.getCommentableId());
        Assert.notNull(temBlog,"该文章不存在");
        User temUser = userService.getById(comment.getUserId());
        Assert.notNull(temUser,"该用户不存在");
        Assert.hasText(comment.getBody(),"评论内容不能为空");

        Comment temComment = null;
        if(0 != comment.getParentId()){
            //表明该评论是对别人评论的回复
            //那么就要对一级评论的评论数加1
            //不记录一级评论以下的评论数量
            temComment = commentService.getById(comment.getParentId());
            temComment.setCommentCount(temComment.getCommentCount() + 1);
            commentService.saveOrUpdate(temComment);
        }

        temBlog.setCommentCount(temBlog.getCommentCount() + 1);
        comment.setCreateTime(LocalDateTime.now());

        blogService.saveOrUpdate(temBlog);
        commentService.save(comment);

        return Result.succ(comment);
    }

    /**
     * @title deleteComment
     * @description 删除评论 操作就应该于评论的操作相反
     * @author davidmorgan
     * @param: comment 评论的实体类
     * @updateTime 2020/12/3 14:51
     * @return: java.lang.Object
     */
    @RequiresAuthentication
    @PostMapping("/deleteReview")
    public Object deleteComment(@Validated @RequestBody CommentDto comment){
        Assert.notNull(comment.getId(),"该评论的数据缺少Id");
        Blog temBlog = blogService.getById(comment.getCommentableId());
        Assert.notNull(temBlog,"该文章不存在");

        Comment temComment = null;

        Long count = 0L;

        List childList= null;

        if(0 != comment.getParentId()){
            //表明该评论是来源对于别人的评论
            count = 1L;
            temComment = commentService.getById(comment.getParentId());
            temComment.setCommentCount(temComment.getCommentCount() - 1);
            commentService.saveOrUpdate(temComment);

            commentService.removeById(comment.getId());

        }else{
            //表明这个评论是一级评论，那么下面的子评论都应该删除
            childList = new ArrayList();
            IPage pageData = commentService.getSonCommentList(new Page(),new QueryWrapper<Comment>().orderByDesc("create_time"),comment);
            for(Object child: pageData.getRecords()) {
                CommentDto temComDto = (CommentDto) child;
                childList.add(temComDto.getId());
            }
            childList.add(comment.getId());
            count = pageData.getTotal() + 1;

            commentService.removeByIds(childList);
        }


        temBlog.setCommentCount(temBlog.getCommentCount() - count.intValue());

        blogService.saveOrUpdate(temBlog);



        return Result.succ(comment);
    }

    /**
     * @title getcommentList 获取评论列表，根据文章ID和parentID 获取一级评论之后再根据一级评论的ID获取其下面的回复评论
     * @description
     * @author davidmorgan
     * @param: commentDto
     * @updateTime 2020/12/4 17:12
     * @return: java.lang.Object
     */
    @PostMapping("/getCommentList")
    public Object getcommentList(@RequestBody CommentDto commentDto){
        Assert.notNull(commentDto.getCommentableId(),"文章不存在");
        Page page = new Page(commentDto.getPageCount(),commentDto.getPageSize());
        IPage pageData = commentService.getCommentList(page,new QueryWrapper<Comment>().orderByDesc("create_time"),commentDto);

        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("total",pageData.getTotal());
        dataMap.put("size",pageData.getSize());
        dataMap.put("current",pageData.getCurrent());
        dataMap.put("pages",pageData.getPages());
        List records = new ArrayList();
        for(Object i : pageData.getRecords()){
            CommentDto temComDto = (CommentDto)i;
            Map<String,Object> sonMap = null;
            List sonList = null;
            if(null != temComDto.getCommentCount() && temComDto.getCommentCount() > 0){
                sonMap = new HashMap<>();
                sonList = new ArrayList();
                IPage sonPageData = commentService.getSonCommentList(new Page(),new QueryWrapper<Comment>().orderByDesc("create_time"),temComDto);
                for(Object son: sonPageData.getRecords()){
                    CommentDto sonComDto = (CommentDto)son;
                    sonList.add(sonComDto);
                }
                sonMap.put("sonList",sonList);
                sonMap.put("total",sonPageData.getTotal());
                sonMap.put("current",sonPageData.getCurrent());
                sonMap.put("pages",pageData.getPages());

            }
            temComDto.setSonComment(sonMap);
            records.add(temComDto);
        }
        dataMap.put("records",records);
        return Result.succ(dataMap);
    }
}
