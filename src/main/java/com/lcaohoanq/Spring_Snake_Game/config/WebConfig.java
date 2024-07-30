package com.lcaohoanq.Spring_Snake_Game.config;

import com.lcaohoanq.Spring_Snake_Game.filter.UserRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public Filter userRequestFilter() {
        return new UserRequestFilter();
    }

}