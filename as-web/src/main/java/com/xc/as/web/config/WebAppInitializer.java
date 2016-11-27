package com.xc.as.web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by yxc on 2016/11/25.
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected String[] getServletMappings(){
       /* 将DispatcherServlet映射到"／"*/
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses(){
        /*ContextLoaderListener配置类*/
        return new Class<?>[] {RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses(){
        /*DispatcherServlet配置类*/
        return new Class<?>[] {WebConfig.class};
    }
}