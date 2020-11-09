package com.luobo.common.dto;

import lombok.Data;

/**
 * @ClassName PageDto
 * @Description 请描述类的业务用途
 * @Author davidmorgan
 * @Date 2020/11/3 09:30
 * @Version 1.0
 **/
@Data
public class PageDto {
    private Integer userId;
    private Integer currentPage;
    private Integer pageSize;
}
