package com.xc.as.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by yxc on 2016/11/25.
 */

@Configuration
@EnableScheduling
@ComponentScan(basePackages = {"com.xc.as.core"}, excludeFilters = {
        @ComponentScan.Filter(type= FilterType.ANNOTATION, value = EnableWebMvc.class)
})
public class RootConfig {

}
