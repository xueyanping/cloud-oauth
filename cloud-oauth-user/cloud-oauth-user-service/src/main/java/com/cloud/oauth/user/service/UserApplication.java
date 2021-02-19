package com.cloud.oauth.user.service;

import com.cloud.oauth.framework.oauth2.feign.EnableOAuth2ClientFeign;
import com.cloud.oauth.framework.security.access.EnableSecurityAccess;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xueyanping
 * @since 2021/2/16 11:59
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@EnableOAuth2Client
@EnableOAuth2ClientFeign
@EnableSecurityAccess
@EnableCaching
@EnableFeignClients("com.cloud")
@ComponentScan("com.cloud")
public class UserApplication extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/actuator/**", "/v2/api-docs"
                ,"/auth/**").permitAll().and().authorizeRequests()
                .anyRequest().authenticated();
        //,"/auth/**","/test/**"
    }


    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
