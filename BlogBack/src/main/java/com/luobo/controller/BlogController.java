package com.luobo.controller;



import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.common.dto.PageDto;
import com.luobo.common.lang.Result;
import com.luobo.entity.Blog;
import com.luobo.service.BlogService;
import com.luobo.service.UserService;
import com.luobo.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2020-08-06
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    UserService userService;

    @PostMapping    ("/blogs")
    public Result blogs(@RequestBody PageDto pageParams){
        if(pageParams.getCurrentPage() == null || pageParams.getCurrentPage() < 1)
            pageParams.setCurrentPage(1);
        Page page = new Page(pageParams.getCurrentPage(),pageParams.getPageSize());
        IPage pageData =blogService.GetBlogsLeftInUser(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.succ(pageData);
    }


    @GetMapping("/{id}")
    public Result detail(@PathVariable(name="id") Long id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客已被删除");

        return Result.succ(blog);
    }
    @RequiresAuthentication
    @PostMapping("/edit")
    public Result edit(@Validated @RequestBody Blog blog) {

        Blog temp = null;
        if(blog.getId() != null) {
            temp = blogService.getById(blog.getId());
            // 只能编辑自己的文章
            System.out.println(ShiroUtil.getProfile().getId());
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");

        } else {

            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }

        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
        blogService.saveOrUpdate(temp);

        return Result.succ(null);
    }

    @PostMapping("/getMyBlogs")
    public Result getBlgs(@Validated @RequestBody PageDto pageParams){
        Assert.notNull(pageParams.getUserId(),"用户ID为空");
        Page page = new Page(pageParams.getCurrentPage(),pageParams.getPageSize());
        IPage pageData =blogService.GetBlogsByuUserId(page, pageParams.getUserId());
        return Result.succ(pageData);
    }

}
