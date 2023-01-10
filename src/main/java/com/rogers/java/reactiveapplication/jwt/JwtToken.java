package com.rogers.java.reactiveapplication.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.rogers.java.reactiveapplication.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.Map;

@RequiredArgsConstructor
public class JwtToken {

  private final Algorithm algorithm;

  public Mono<Map<String, Claim>> verify(final String token) {
    if (!StringUtils.hasText(token)) {
      return Mono.error(new AuthenticationException("Required token"));
    }

    return Mono.fromCallable(() -> {
      try {
        return JWT.require(algorithm)
            .build()
            .verify(token)
            .getClaims();
      } catch (JWTVerificationException e) {
        throw new AuthenticationException("Invalid token");
      }

      catch (Exception e) {
        throw new AuthenticationException("Invalid token");
      }
    });
  }


}
