package com.luobo.service;

import com.luobo.entity.Vote;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luobo.mapper.VoteMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author David
 * @since 2020-11-25
 */
public interface VoteService extends IService<Vote> {
    /**
     * @title getTheVote
     * @description 根据用户ID和文章ID查询点赞信息是否存在
     * @author davidmorgan
     * @param: userId 用户ID
     * @param: blogId 文章ID
     * @updateTime 2020/11/26 10:15
     * @return: 如果找不到的话返回true否则返回false
     */
    Vote getTheVote(Long userId,Integer blogId);
}
