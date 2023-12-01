package com.example.techolution.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ResourceSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager UserDetailsService() {

        UserDetails pankaj = User.builder()
                .username("pankaj")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails sharad = User.builder()
                .username("sharad")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(pankaj, sharad, admin);
    }
}













