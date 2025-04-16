package com.first.first_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.first.first_project.Entity.JwtEntity;
import com.first.first_project.Entity.JwtResponse;
import com.first.first_project.util.JwtTokenUtil;

@RestController
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	@Qualifier("jwtUserDetailsService")
	private UserDetailsService userDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtEntity authenticationRequest) throws Exception {
		if (authenticationRequest == null || 
			authenticationRequest.getEmail() == null || 
			authenticationRequest.getEmail().isEmpty() ||
			authenticationRequest.getPassword() == null || 
			authenticationRequest.getPassword().isEmpty()) {
			return ResponseEntity.badRequest().body("Email and password are required");
		}

		try {
			// Create authentication token
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				authenticationRequest.getEmail(), 
				authenticationRequest.getPassword()
			);

			// Authenticate
			authenticationManager.authenticate(authToken);

			// Load user details and generate token
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
			final String token = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new JwtResponse(token));
		} catch (DisabledException e) {
			return ResponseEntity.badRequest().body("USER_DISABLED");
		} catch (BadCredentialsException e) {
			return ResponseEntity.badRequest().body("INVALID_CREDENTIALS");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Authentication failed: " + e.getMessage());
		}
	}
  
}
