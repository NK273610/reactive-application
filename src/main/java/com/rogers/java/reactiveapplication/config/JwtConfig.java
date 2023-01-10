package com.rogers.java.reactiveapplication.config;

import com.auth0.jwt.algorithms.Algorithm;
import com.rogers.java.reactiveapplication.jwt.JwtToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

  @Bean
  JwtToken jwtToken() {
    final Algorithm algorithm = Algorithm.HMAC256("jhasbdhjsadhjbbasbcasbjcb");
    return new JwtToken(algorithm);
  }
}
