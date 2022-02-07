package com.av3.springcloudapp.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (request.getServletPath()

        .equals("/api/v1/login")
        || request.getServletPath().equals("/api/v1/auth/refresh")) {
      filterChain.doFilter(request, response);
      return;
    }

    String authorizationHeader = request.getHeader("AUTHORIZATION");

    if (authorizationHeader == null) {
      filterChain.doFilter(request, response);
      return;
    }

    try {

      if (!authorizationHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
      }

      String token = authorizationHeader.split(" ")[1];
      Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
      JWTVerifier verifier = JWT.require(algorithm).build();
      DecodedJWT decodedJWT = verifier.verify(token);
      String username = decodedJWT.getSubject();
      Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority("USER"));

      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null,
          authorities);
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      filterChain.doFilter(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      Map<String, String> error = new HashMap<>();
      error.put("error_message", e.getMessage());
      response.setContentType("application/json");
      new ObjectMapper().writeValue(response.getOutputStream(), error);
    }

  }

}
