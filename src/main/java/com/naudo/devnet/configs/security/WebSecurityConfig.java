package com.naudo.devnet.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		   http
           .authorizeHttpRequests((authorize) -> authorize
                   .anyRequest().permitAll())
           .csrf().disable();
		   return http.build();
	}
	
}
