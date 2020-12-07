package com.luobo.mapper;

import com.luobo.entity.Vote;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.shiro.util.Assert;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author David
 * @since 2020-11-25
 */
public interface VoteMapper extends BaseMapper<Vote> {
    /**
     * @title fingVoteByUserIdAndBlogId
     * @description gengju
     * @author davidmorgan
     * @param: userId 用户ID
     * @param: blogId 文章ID
     * @updateTime 2020/11/26 10:29
     * @return: com.luobo.entity.Vote
     */
    Vote fingVoteByUserIdAndBlogId(Long userId,Integer blogId);
}
