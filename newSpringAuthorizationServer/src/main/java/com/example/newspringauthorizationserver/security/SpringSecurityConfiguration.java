package com.example.newspringauthorizationserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfiguration {

    @Bean
    SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(authz->authz.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        // Login credentials: username = aryan, password = aryan
        // {noop} = no-op encoder (plain text), for dev only
        UserDetails user = User.withUsername("aryan")
                .password("{noop}aryan")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
