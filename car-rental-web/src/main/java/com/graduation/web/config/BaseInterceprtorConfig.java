package com.graduation.web.config;

import com.graduation.web.interceptor.VerifyAuthorityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption
 * @Author fuxiaoxiang2
 * @Create 2019/1/10 16:24
 */
@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan("com.graduation.web")
public class BaseInterceprtorConfig implements WebMvcConfigurer{
    @Bean
    public VerifyAuthorityInterceptor demoInterceptor() {
        System.out.println("ok");
        return new VerifyAuthorityInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {// 2
        registry.addInterceptor(demoInterceptor());
    }
}
