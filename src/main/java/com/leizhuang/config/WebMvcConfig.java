package com.leizhuang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LeiZhuang
 * @date 2021/10/25 15:44
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        //跨域配置
        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
    }
}
