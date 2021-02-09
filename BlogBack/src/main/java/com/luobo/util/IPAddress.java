package com.luobo.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luobo.common.lang.Result;
import com.luobo.entity.User;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName IPAddress
 * @Description 获取当前用户的ip地址
 * @Author davidmorgan
 * @Date 2021/2/9 14:33
 * @Version 1.0
 **/
public class IPAddress {
    /**
     * 获取访问者IP
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            if(ip.contains("../")||ip.contains("..\\")){
                return "";
            }
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                ip= ip.substring(0, index);
            }
            if(ip.contains("../")||ip.contains("..\\")){
                return "";
            }
            return ip;
        } else {
            ip=request.getRemoteAddr();
            if(ip.contains("../")||ip.contains("..\\")){
                return "";
            }
            if(ip.equals("0:0:0:0:0:0:0:1")){
                ip="127.0.0.1";
            }
            return ip;
        }

    }

    /**
     * @title isRead
     * @description 检测该用户（IP）最近是否访问过该
     * @author davidmorgan
     * @param: id 文章ID
     * @param: ip 访问用户的IP
     * @updateTime 2021/2/9 15:23
     * @return: java.lang.Boolean true 代表该用户最近访问过该文章了 false 代表改文章没有访问过
     */
    public static Boolean isRead(Long id,String ip){

        Jedis jedis = new Jedis("localhost");

        String str = jedis.get(ip);

        if(str == null){
            jedis.set(ip,"ready_"+id);
            //设置验证码过期时间 过期时间为30分钟
            jedis.expire(ip,864000);
            jedis.close();
            return false;
        }

        return true;
    }
}
