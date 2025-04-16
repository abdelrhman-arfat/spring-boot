package com.first.first_project.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
  @Override
  public void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
    if (request.getServletPath().equals("/authenticate")) {
      filterChain.doFilter(request, response);
      return;
    }

    final String authorizationToken = request.getHeader("Authorization");
    if(authorizationToken != null && authorizationToken.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
    } else {
      logger.warn("JWT Token does not begin with Bearer String");
      throw new ServletException("JWT Token does not begin with Bearer String");
    }
  }
  
}
