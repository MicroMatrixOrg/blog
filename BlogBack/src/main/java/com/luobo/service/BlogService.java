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
    Page<Blog> GetBlogsLeftInUser (Page<Blog> page, QueryWrapper<Blog> wrapper);
}
