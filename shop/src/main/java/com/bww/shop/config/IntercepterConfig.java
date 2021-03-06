package com.bww.shop.config;

import com.bww.shop.intercepter.LoginIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginIntercepter()).addPathPatterns("/user/api/v1/*/**");
//        registry.addInterceptor(new LoginIntercepter()).addPathPatterns("/admin/api/v1/*/**");
        registry.addInterceptor(new LoginIntercepter()).excludePathPatterns("/**/user/login");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
