package com.mud.config;

import com.mud.interceptor.AccessTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by leeesven on 17/8/22.
 */

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Bean
    public AccessTokenInterceptor getAccessTokenInterceptor(){
        return new AccessTokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAccessTokenInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
