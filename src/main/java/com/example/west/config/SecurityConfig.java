package com.example.west.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author: chenxu
 * @date: 2020/6/14 9:02
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单登录。跳转到security默认的登录表单页
                // http.httpBasic() //basic登录
                .and()
                .authorizeRequests() // 对请求授权
                .antMatchers("/async/**").permitAll() //允许所有人访问/noAuth
                .anyRequest() // 任何请求
                .authenticated()// 需要身份认证
        ;
    }
}
