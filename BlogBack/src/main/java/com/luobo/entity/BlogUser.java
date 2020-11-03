package com.luobo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName BlogUser
 * @Description 请描述类的业务用途
 * @Author davidmorgan
 * @Date 2020/11/2 18:03
 * @Version 1.0
 **/
@Data
public class BlogUser {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private String title;

    private String description;

    private String content;

    private LocalDateTime created;

    private Integer status;

    private String username;
}
