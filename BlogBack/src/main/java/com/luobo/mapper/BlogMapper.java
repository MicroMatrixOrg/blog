package com.luobo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.common.dto.PageDto;
import com.luobo.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2020-08-06
 */
public interface BlogMapper extends BaseMapper<Blog> {
    /**
     * @title findBlogs
     * @description 查找博客并根据用户ID左连接用户
     * @author davidmorgan
     * @updateTime 2020/11/2 16:59
     * @return: java.util.List<com.luobo.entity.Blog>
     */
    List<Blog> findBlogs(Page<Blog> page, @Param(Constants.WRAPPER) QueryWrapper<Blog> wrapper, Long currentUserId, String title);

    /**
     * @title findBlogsByUserId
     * @description 根据用户ID获取博客
     * @author davidmorgan
     * @param: page 分页参数
     * @param: userId 用户ID
     * @updateTime 2020/11/26 11:01
     * @return: java.util.List<com.luobo.entity.Blog>
     */
    List<Blog> findBlogsByUserId(Page<Blog> page, Long userId);

}
