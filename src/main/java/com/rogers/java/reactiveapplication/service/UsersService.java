package com.rogers.java.reactiveapplication.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UsersService {

  public Mono<Void> deleteUserById(final UUID id) {
    return Mono.fromRunnable(() -> {

    });
  }
}
