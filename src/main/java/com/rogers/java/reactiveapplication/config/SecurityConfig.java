package com.rogers.java.reactiveapplication.config;

import com.rogers.java.reactiveapplication.jwt.JwtToken;
import com.rogers.java.reactiveapplication.security.AuthnWebTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {

  @Autowired
  JwtToken jwtToken;

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity http) {
    return http
        .logout().disable()
        .csrf().disable()
        .authorizeExchange()
        .anyExchange().authenticated()
        .and()
        .addFilterAt(
            new AuthnWebTokenFilter(jwtToken),
            SecurityWebFiltersOrder.AUTHENTICATION
        )
        .build();
  }
}
