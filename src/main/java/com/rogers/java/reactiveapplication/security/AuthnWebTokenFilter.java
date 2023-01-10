package com.rogers.java.reactiveapplication.security;

import com.rogers.java.reactiveapplication.exception.AuthenticationException;
import com.rogers.java.reactiveapplication.jwt.JwtToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Map;


@RequiredArgsConstructor
@Slf4j
public class AuthnWebTokenFilter implements WebFilter {


  private final JwtToken jwtToken;
  private final AuthServerSecurityContextRepository securityContextRepository;


  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    final ServerHttpRequest request = exchange.getRequest();
    final String authorization = request.getHeaders().getFirst("Authorization");
    final String token = parseToken(authorization);
    if (StringUtils.hasText(token)) {
      return jwtToken.verify(token)
          .flatMap(claims -> securityContextRepository.save(exchange, toSecurityContext(token)))
          .then(chain.filter(exchange));
    }
    return chain.filter(exchange);
  }

  private SecurityContext toSecurityContext(String token) {
    final SecurityContext securityContext = new SecurityContextImpl();
    final var authentication = new AuthenticationToken(token, true);
    securityContext.setAuthentication(authentication);
    return securityContext;
  }

  private String parseToken(final String authorization) {
    if (!StringUtils.hasText(authorization)) {
      return null;
    }
    final String[] arr = authorization.replaceAll("\\s+", " ").trim().split("\\s");
    if (!(arr != null && arr.length == 2)) {
      throw new AuthenticationException("Invalid token");
    }
    if (!"bearer".equalsIgnoreCase(arr[0])) {
      throw new AuthenticationException("Invalid bearer token");
    }
    return arr[1];
  }
}
