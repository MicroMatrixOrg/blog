package com.luobo.controller;



import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.common.dto.PageDto;
import com.luobo.common.lang.Result;
import com.luobo.entity.Blog;
import com.luobo.entity.Vote;
import com.luobo.service.BlogService;
import com.luobo.service.CommentService;
import com.luobo.service.UserService;
import com.luobo.service.VoteService;
import com.luobo.util.IPAddress;
import com.luobo.util.JwtUtils;
import com.luobo.util.ShiroUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

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

    @Autowired
    VoteService voteService;

    @Autowired
    CommentService commentService;



    @Autowired
    JwtUtils jwtUtils;

    @RequiresAuthentication
    @PostMapping("/deleteBlog")
    public Result delete(@RequestBody Blog tBlog){
        Long lBlogId =tBlog.getId();
        Blog blog = blogService.getById(lBlogId);
        Assert.notNull(blog,"该博客不存在");
        //删除博客，同时删除博客相关的表
        //1、点赞
        Integer voreCount = voteService.deleteVote(lBlogId);
        Assert.notNull(voreCount,"评论删除错误");
        //2、评论
        Integer commCount = commentService.deleteComment(lBlogId);
        Assert.notNull(commCount,"评论删除失败");
        //3、文件暂时为未完整


        Boolean dele = blogService.removeById(lBlogId);
        Assert.isTrue(dele,"删除失败");
        return Result.succ(blog);
    }

    /**
     * @title blogs
     * @description
     * @author davidmorgan
     * @param: pageParams
     * @param: servletRequest 主要用户获取用户的Token
     * @updateTime 2020/11/27 14:19
     * @return: com.luobo.common.lang.Result
     */
    @PostMapping("/blogs")
    public Result blogs(@RequestBody PageDto pageParams, ServletRequest servletRequest){
        if(pageParams.getCurrentPage() == null || pageParams.getCurrentPage() < 1)
            pageParams.setCurrentPage(1);
        Page page = new Page(pageParams.getCurrentPage(),pageParams.getPageSize());

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("UserToken");
        Claims claims = jwtUtils.getClaimByToken(jwt);

        Long currentUserId = null;
        if(null != claims){
            currentUserId =Long.parseLong(claims.get("sub").toString());
        }
        IPage pageData =blogService.GetBlogsLeftInUser(page, new QueryWrapper<Blog>().orderByDesc("created"),currentUserId);
        return Result.succ(pageData);
    }

    /**
     * @title detail
     * @description 根据对应的博客ID获取博客详情
     * @author davidmorgan
     * @param: id
     * @updateTime 2020/11/25 17:36
     * @return: com.luobo.common.lang.Result
     */
    @GetMapping("/{id}")
    public Result detail(@PathVariable(name="id") Long id ,HttpServletRequest request){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客已被删除");
        String ip = IPAddress.getIpAddr(request);

        Boolean isRead = IPAddress.isRead(id,ip);
        if(!isRead){
            Blog temp = new Blog();
            Integer count = blog.getReadCount();
            count ++;
            blog.setReadCount(count);
            BeanUtil.copyProperties(blog,temp);
            blogService.saveOrUpdate(temp);
        }
        return Result.succ(blog);
    }


    /**
     * @title edit
     * @description 更新对应的博客内容
     * @author davidmorgan
     * @param: blog
     * @updateTime 2020/11/25 17:37
     * @return: com.luobo.common.lang.Result
     */
    @RequiresAuthentication
    @PostMapping("/edit")
    public Result edit(@Validated @RequestBody Blog blog) {

        Blog temp = null;
        if(blog.getId() != null) {
            temp = blogService.getById(blog.getId());
            // 只能编辑自己的文章
//            System.out.println(ShiroUtil.getProfile().getId());
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

    /**
     * @title getBlgs
     * @description 获得该用户下的博客
     * @author davidmorgan
     * @param: pageParams 分页参数
     * @updateTime 2020/11/25 17:37
     * @return: com.luobo.common.lang.Result
     */
    @RequiresAuthentication
    @PostMapping("/getMyBlogs")
    public Result getBlgs(@Validated @RequestBody PageDto pageParams){
        Assert.notNull(pageParams.getUserId(),"用户ID为空");
        Page page = new Page(pageParams.getCurrentPage(),pageParams.getPageSize());
        IPage pageData =blogService.GetBlogsByuUserId(page, pageParams.getUserId());
        return Result.succ(pageData);
    }


}
