package com.luobo.common.dto;

import lombok.Data;

/**
 * @ClassName MailDto
 * @Description 邮件实体类便于获取和传输
 * @Author davidmorgan
 * @Date 2020/10/10 10:58
 * @Version 1.0
 **/

@Data
public class MailDto {
    private String username;//收信人称谓
    private String toMail;//收信人
    private String subject;//主题
    private String content;//内容
    private String filePath;//附件路径
    private String rscPath;//图片路径
    private String rscId;//图片ID
    private String verification;//验证码
}
