package com.rogers.java.reactiveapplication.controller;

import com.rogers.java.reactiveapplication.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping({"/users"})
@Slf4j
public class UserController {


  @Autowired
  UsersService userService;

  @DeleteMapping("/{id}")
  public Mono<Void> deleteUserById(@PathVariable("id") final UUID id) {
    return userService.deleteUserById(id);
  }
}
