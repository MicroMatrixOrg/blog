package com.luobo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2020-08-06
 */
public interface BlogService extends IService<Blog> {
    /**
     * @title GetBlogsLeftInUser
     * @description 获取指定的博客左连接用户信息表
     * @author davidmorgan
     * @param: page
     * @param: wrapper
     * @updateTime 2020/11/26 11:02
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.luobo.entity.Blog>
     */
    Page<Blog> GetBlogsLeftInUser (Page<Blog> page, QueryWrapper<Blog> wrapper,Long currentuserId);

    /**
     * @title GetBlogsByuUserId
     * @description 根据用户ID获取他所写的博客
     * @author davidmorgan
     * @param: page
     * @param: userId
     * @updateTime 2020/11/26 11:03
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.luobo.entity.Blog>
     */
    Page<Blog> GetBlogsByuUserId (Page<Blog> page, Integer userId);
}
