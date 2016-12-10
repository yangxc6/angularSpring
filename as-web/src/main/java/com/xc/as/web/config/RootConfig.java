package com.xc.as.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by yxc on 2016/11/25.
 */

@Configuration
@EnableScheduling
@ComponentScan(basePackages = {"com.xc.as.core", "com.xc.as.web"})
public class RootConfig {

}
