package com.luobo.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
/**
 * @title 登录信息的实体类
 * @description 将登录信息合成一个实体类，便于传输
 * @author davidmorgan
 * @updateTime 2020/11/26 11:12
 */
@Data
public class LoginDto implements Serializable {

    @NotBlank(message = "昵称不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
