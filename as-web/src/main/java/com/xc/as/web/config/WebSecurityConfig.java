package com.xc.as.web.config;

import com.xc.as.web.common.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Created by yxc on 2016/12/8.
 */
@Component
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/**").hasRole("USER").and().exceptionHandling();

        // 自定义登录页面
        http.csrf().disable().formLogin().loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?error=true")
                .loginProcessingUrl("/j_spring_security_check").usernameParameter("j_username")
                .passwordParameter("j_password").permitAll();

        // 自定义注销
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true);

        // session管理
        http.sessionManagement().sessionFixation().changeSessionId().maximumSessions(100).expiredUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        CustomUserDetailsService userDetailsService = new CustomUserDetailsService();
        return userDetailsService;
    }
}
