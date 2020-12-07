package com.luobo.util;

import com.luobo.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;
/**
 * @title shiro 工具类 二次开发
 * @description
 * @author davidmorgan
 * @updateTime 2020/11/26 17:32
 */
public class ShiroUtil {

    /**
     * @title getProfile
     * @description 获取用户信息
     * @author davidmorgan
     * @updateTime 2020/11/26 17:33
     * @return: com.luobo.shiro.AccountProfile
     */
    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }


}
