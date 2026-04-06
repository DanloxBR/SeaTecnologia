package com.danieldev.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}123qwe!@#")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("{noop}123qwe123")
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()

                .antMatchers(HttpMethod.POST, "/clientes").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/clientes/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/clientes/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/clientes/**").hasAnyRole("ADMIN", "USER")

                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}