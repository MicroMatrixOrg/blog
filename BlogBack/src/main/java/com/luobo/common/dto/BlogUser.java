package com.luobo.common.dto;


import com.luobo.entity.Blog;
import lombok.Data;

/**
 * @ClassName BlogUser
 * @Description 扩展Blog表的数据种类
 * @Author davidmorgan
 * @Date 2020/11/2 18:03
 * @Version 1.0
 **/
@Data
public class BlogUser extends Blog {
    private String username;
    private Long isVote;
}
