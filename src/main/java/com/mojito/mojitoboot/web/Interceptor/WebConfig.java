package com.mojito.mojitoboot.web.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * @Auther: Mojito
 * @Date: 2019/4/1 20:03
 * @Description:
 */
@Configuration
//@EnableWebMvc = 继承WebMvcAutoConfiguration
public class WebConfig extends WebMvcAutoConfiguration {

    @Autowired
    HandlerInterceptor HandlerInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LocaleChangeInterceptor());
//        registry.addInterceptor(new ThemeChangeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
//        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");

          registry.addInterceptor(HandlerInterceptor).addPathPatterns("/**");
    }
}


