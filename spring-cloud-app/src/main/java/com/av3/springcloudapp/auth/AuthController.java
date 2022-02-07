package com.av3.springcloudapp.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.av3.springcloudapp.common.status.ForbiddenStatus;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "auth")
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping(path = "/refresh")
  public void refreshtoken(HttpServletRequest request, HttpServletResponse response)
      throws StreamWriteException, DatabindException, IOException {

    String authorizationHeader = request.getHeader("AUTHORIZATION");

    if (authorizationHeader == null)
      throw new ForbiddenStatus("Token not provided on headers");

    try {
      if (!authorizationHeader.startsWith("Bearer "))
        throw new ForbiddenStatus("Token not formatted as expected.");

      String refresh_token = authorizationHeader.split(" ")[1];
      Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
      JWTVerifier verifier = JWT.require(algorithm).build();
      DecodedJWT decodedJWT = verifier.verify(refresh_token);
      String username = decodedJWT.getSubject();
      User user = (User) authService.loadUserByUsername(username);
      Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority("USER"));

      String access_token = JWT.create()
          .withSubject(user.getUsername())
          .withExpiresAt(new Date(System.currentTimeMillis() + 100 * 60 * 1000))
          .withIssuer(request.getRequestURL().toString())
          .sign(algorithm);

      Map<String, String> tokens = new HashMap<>();
      tokens.put("access_token", access_token);
      tokens.put("refresh_token", refresh_token);
      response.setContentType("application/json");
      new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    } catch (Exception e) {
      e.printStackTrace();
      Map<String, String> error = new HashMap<>();
      error.put("error_message", e.getMessage());
      response.setContentType("application/json");
      new ObjectMapper().writeValue(response.getOutputStream(), error);
    }
  }
}
