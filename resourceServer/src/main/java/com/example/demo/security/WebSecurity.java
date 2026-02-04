package com.example.demo.security;

import com.example.demo.converter.KeyCloakConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception{

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyCloakConverter());

        http.authorizeHttpRequests(authz->
                        authz
//                                .requestMatchers(HttpMethod.GET,"/user/**").hasAuthority("SCOPE_profile")
                                .requestMatchers("/user/**").hasRole("developer")
                                .anyRequest().authenticated())
                .oauth2ResourceServer(auth->
                        auth.jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));
        return http.build();
    }
}
