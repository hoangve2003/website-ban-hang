package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    MyInterceptor interceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor li = new LocaleChangeInterceptor();
        li.setParamName("lang");
        registry.addInterceptor(li).addPathPatterns("/**");
        registry.addInterceptor(interceptors).addPathPatterns("/admin/**");
    }
}
