package com.mrdong.gaeasso.intercept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: liudong
 * @date: 2019-06-13 18:45
 **/
@Component
public class WebConfigAdapter implements WebMvcConfigurer {

    @Autowired
    private TokenIntercept tokenIntercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePatterns = new ArrayList<>();
        excludePatterns.add("/pages/**");
        excludePatterns.add("/public/**");
        excludePatterns.add("/resource/**");
        registry.addInterceptor(tokenIntercept).addPathPatterns("/**").excludePathPatterns(excludePatterns);
    }
}
