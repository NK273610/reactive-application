package com.rogers.java.reactiveapplication.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class temp {

  public static void main(String[] args) {
    final var algorithm = Algorithm.HMAC256("jhasbdhjsadhjbbasbcasbjcb");
    final Instant issuedAt = Instant.now();
    final Instant expiresAt = issuedAt.plus(Duration.ofMinutes(150000));
    var jwt = JWT.create()
        .withIssuedAt(Date.from(issuedAt))
        .withExpiresAt(Date.from(expiresAt))
        .withClaim("data", "{\"hello\" : \"there\"}")
        .sign(algorithm);

    System.out.println("Hello");
  }
}
