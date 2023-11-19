package com.example.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class Security {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequest->
                    authorizeRequest.requestMatchers(new AntPathRequestMatcher("/order-service/**"), new AntPathRequestMatcher("/h2-console/**")).permitAll())
                .headers(headers->
                        headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }


}
