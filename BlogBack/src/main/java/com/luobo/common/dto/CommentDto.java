package com.luobo.common.dto;

import com.luobo.entity.Comment;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CommentDto
 * @Description 请描述类的业务用途
 * @Author davidmorgan
 * @Date 2020/12/3 15:31
 * @Version 1.0
 **/
@Data
public class CommentDto extends Comment {
    private Integer pageSize;
    private Integer pageCount;
    private String username;
    private String avatar;
    private String commentedUserName;
    private Map<String,Object> sonComment;
}
