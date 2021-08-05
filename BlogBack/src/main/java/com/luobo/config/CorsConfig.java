package com.luobo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

//    /**
//     * @title addCorsMappings
//     * @description 跨域头部配置
//     * @author davidmorgan
//     * @param: registry
//     * @updateTime 2020/11/26 11:10
//     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS").allowCredentials(true).maxAge(3600).allowedHeaders("*");
//    }

    @Bean
    public CorsFilter corsFilter() {
        //新建跨域配置
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许所有请求源(url)
        corsConfiguration.addAllowedOrigin("*");
        //允许所有请求头信息
        corsConfiguration.addAllowedHeader("*");
        //允许所有请求url中包含的方法名
        corsConfiguration.addAllowedMethod("*");
        //允许所有请求url中包含用户的凭证信息
        corsConfiguration.setAllowCredentials(true);
        //新建基于url的配置源信息
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //将跨域规则注册进去(类似于List中的add方法)
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
