package com.rogers.java.reactiveapplication.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

@EqualsAndHashCode(callSuper = true)
public class AuthenticationToken extends AbstractAuthenticationToken {
  @Getter
  private String token;
  private final transient Object principal;

  /**
   * Constructor for creating oauth authentication token.
   * The authentication object is unauthenticated and authentication should be set to true
   * explicitly.
   *
   * @param token that is received from the client.
   */
  public AuthenticationToken(final String token, final boolean authenticated) {
    super(null);
    this.token = token;
    this.principal = null;
    this.setAuthenticated(authenticated);
  }

  /**
   * Constructor for creating oauth authentication token.
   * The authentication object is unauthenticated and authentication should be set to true
   * explicitly.
   *
   * @param token that is received from the client.
   */
  public AuthenticationToken(final Object principal, final String token) {
    super(null);
    this.principal = principal;
    this.token = token;
    this.setAuthenticated(false);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return this.principal;
  }

  @Override
  public void eraseCredentials() {
    super.eraseCredentials();
    token = null;
  }
}
