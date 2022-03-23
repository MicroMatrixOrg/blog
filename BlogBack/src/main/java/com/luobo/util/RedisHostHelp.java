package com.luobo.util;

/**
 * @ClassName RedisHostHekp
 * @Description 请描述类的业务用途
 * @Author davidmorgan
 * @Date 2022/3/23 下午8:14
 * @Version 1.0
 **/
public class RedisHostHelp {
    private static String redisHost;
    private static Integer redisProt;
    private static String redisPass;

    public static String getRedisHost(){
        return redisHost;
    }
    public static void setRedisHost(String redisHost){
        RedisHostHelp.redisHost = redisHost;
    }
    public static Integer getRedisPort(){
        return redisProt;
    }
    public static void  setRedisPort(Integer redisProt){
        RedisHostHelp.redisProt = redisProt;
    }
    public static String getRedisPass(){
        return redisPass;
    }
    public static void setRedisPass(String redisPass){
        RedisHostHelp.redisPass = redisPass;
    }
}
