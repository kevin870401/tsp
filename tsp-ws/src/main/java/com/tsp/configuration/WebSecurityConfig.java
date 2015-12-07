package com.tsp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests().anyRequest().permitAll();
      /*
        http
            .authorizeRequests()
                .antMatchers("/", "/authorization_code","/people").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/people")
                .permitAll()
                .and()
            .logout()
                .permitAll();
      */
    }


}