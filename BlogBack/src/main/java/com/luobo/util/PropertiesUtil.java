package com.luobo.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName PropertiesUtil
 * @Description 请描述类的业务用途
 * @Author davidmorgan
 * @Date 2022/3/23 下午12:58
 * @Version 1.0
 **/
@Component
public class PropertiesUtil {
    @Autowired
    private Environment environment;
    private static Environment env;

    //PostConstruct注解不可以有参数的
    @PostConstruct
    public void init(){
        env = this.environment;
    }

    public static String getType(String key){
        System.out.println(env);
        String type = env.getProperty(key);
        return type;
    }

    @Value("${shiro-redis.redis-manager.host}")
    private String redisHost;
    @Value("${shiro-redis.redis-manager.port}")
    private Integer redisPort;
    @Value("${shiro-redis.redis-manager.password}")
    private String redisPass;

    @Bean
    public int initStatic(){
        RedisHostHelp.setRedisHost(redisHost);
        RedisHostHelp.setRedisPort(redisPort);
        RedisHostHelp.setRedisPass(redisPass);
        return 0;
    }

}


