package com.poddo.edgeservice.security;

import com.poddo.edgeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.httpBasic();
        httpSecurity.authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/channels/{id}/block").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/channels/{id}/unblock").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/channels/{id}/update").hasAnyAuthority("USER")
                .mvcMatchers(HttpMethod.POST, "/channels/{id}/subscribe").hasAnyAuthority("USER")
                .mvcMatchers(HttpMethod.POST, "/channels/{id}/unsubscribe").hasAnyAuthority("USER")
                .mvcMatchers(HttpMethod.POST, "/users/admin").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/comments/{id}").hasAnyAuthority("USER", "ADMIN")
                .anyRequest().permitAll();
    }

}