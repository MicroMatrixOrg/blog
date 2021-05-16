package com.luobo.common.dto;

import com.luobo.entity.Blog;
import lombok.Data;

/**
 * @ClassName PageDto
 * @Description 分页的类
 * @Author davidmorgan
 * @Date 2020/11/3 09:30
 * @Version 1.0
 **/
@Data
public class PageDto  extends Blog {
    private Integer currentPage;
    private Integer pageSize;

    @Override
    public String getTitle() {
        if(null == super.getTitle() || super.getTitle().isEmpty()){
            return "";
        }
        return super.getTitle();
    }
}
