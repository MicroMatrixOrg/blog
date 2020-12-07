package com.luobo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author David
 * @since 2020-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论主键

     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 评论主题
     */
    private String title;

    /**
     * 评论内容
     */
    private String body;

    /**
     * 评论文章的ID
     */
    private Long commentableId;

    /**
     * 记录被回复人的ID(话题发起最初人的ID)否则为0
     */
    private Long parentId;

    /**
     * 点赞该评论
     */
    private Long voteCount;

    /**
     * 回复该评论的数量
     */
    private Long commentCount;

    /**
     * 评论人的ID
     */
    private Long userId;

    /**
     *当回复他人的时候此处记录被回复人的ID
     */
    private Long commentedUserId;

    /**
     * 评论发布时间
     */
    private LocalDateTime createTime;
}
