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
     * @description 找到该用户在该文章下的点赞
     * @author davidmorgan
     * @param: userId 用户ID
     * @param: blogId 文章ID
     * @updateTime 2020/11/26 10:29
     * @return: com.luobo.entity.Vote
     */
    Vote fingVoteByUserIdAndBlogId(Long userId,Integer blogId);

    /**
     * @title deleteVoteByBlogId
     * @description 删除文章相关的点赞信息
     * @author davidmorgan
     * @param: blogId 文章ID
     * @updateTime 2021/3/7 12:47
     */
    void deleteVoteByBlogId(Integer blogId);
}
