package com.guo.config;

import com.guo.interceptor.StudentLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new StudentLoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/stuLogin","/adminPage","/adminLogin","/static/**");
    }
}
