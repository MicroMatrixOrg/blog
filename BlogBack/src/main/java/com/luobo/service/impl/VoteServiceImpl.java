package com.luobo.service.impl;

import com.luobo.entity.Vote;
import com.luobo.mapper.VoteMapper;
import com.luobo.service.VoteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author David
 * @since 2020-11-25
 */
@Service
public class VoteServiceImpl extends ServiceImpl<VoteMapper, Vote> implements VoteService {

    @Autowired
    VoteMapper voteMapper;

    /**
     * @title getTheVote
     * @description 根据用户ID和文章ID查询点赞信息是否存在
     * @author davidmorgan
     * @param: userId 用户ID
     * @param: blogId 文章ID
     * @updateTime 2020/11/26 10:15
     * @return: 如果找到的话返回Vote
     */
    @Override
    public Vote getTheVote(Long userId, Integer blogId) {
        Vote vote = voteMapper.fingVoteByUserIdAndBlogId(userId,blogId);
        return vote;
    }

    /**
     * @title deleteVote
     * @description 删除点赞信息
     * @author davidmorgan
     * @param: blogId
     * @updateTime 2021/3/7 12:50
     */
    @Override
    public int deleteVote(Long blogId) {
        Map<String,Object> deleMap = new HashMap<>();
        deleMap.put("voteable_id",blogId);
        return voteMapper.deleteByMap(deleMap);
    }
}
