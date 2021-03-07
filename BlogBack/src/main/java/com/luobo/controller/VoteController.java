package com.luobo.controller;



import cn.hutool.core.bean.BeanUtil;
import com.luobo.common.lang.Result;
import com.luobo.entity.Blog;
import com.luobo.entity.Vote;
import com.luobo.service.BlogService;
import com.luobo.service.VoteService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * @title 点赞控制器
 * @description
 * @author davidmorgan
 * @updateTime 2020/11/25 17:14
 */
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author David
 * @since 2020-11-25
 */
@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    BlogService blogService;

    @Autowired
    VoteService voteService;

    /**
     * @title likeArticle
     * @description 点赞或取消点赞
     * @author davidmorgan
     * @param: vote
     * @updateTime 2020/11/25 17:35
     * @return: com.luobo.common.lang.Result
     */
    @RequiresAuthentication
    @PostMapping("/like")
    public Result likeArticle(@RequestBody Vote vote){
        Assert.notNull(vote.getVoteableId(),"博客ID存在错误");
        Blog blog = blogService.getById(vote.getVoteableId());
        Assert.notNull(blog,"该博客已被删除");
//      查询是否有点过赞
        Vote hasVote = voteService.getTheVote(vote.getUserId(),vote.getVoteableId());

        Blog temp = new Blog();
        Vote tempVote = new Vote();
        Integer count = blog.getVoteCount();

        Boolean isVote = false;//取消点赞

        if(null == hasVote){
            isVote = true;
            count ++;
            temp.setVoteCount(count);
        }else{
            isVote = false;
            count -- ;
            temp.setVoteCount(count);
        }
        BeanUtil.copyProperties(blog,temp,"voteCount");
        BeanUtil.copyProperties(vote,tempVote);

        if(null == hasVote){
            voteService.save(tempVote);
        }else{
            voteService.removeById(hasVote.getId());
        }
        blogService.saveOrUpdate(temp);

        return Result.succ(isVote);
    }
}
