package com.example.myapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapi.auth.AuthRequest;
import com.example.myapi.auth.AuthResponse;
import com.example.myapi.jwt.JwtTokenUtil;
import com.example.myapi.model.User;

@RestController
public class LoginController {
	@Autowired
	AuthenticationManager authManager;
	@Autowired
	JwtTokenUtil jwtUtil;

	@PostMapping("/login")
	public String login(@Valid AuthRequest request) {
		System.out.println("LOGIN");
		try {
			org.springframework.security.core.Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getUsername(), request.getPassword()));

			User user = (User) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user);
			AuthResponse response = new AuthResponse(user.getUsername(), accessToken);
			String result=response.getAccessToken();
			//return ResponseEntity.ok().body(response.getAccessToken());
			return result;

		} catch (BadCredentialsException ex) {
			//return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			return "false";
		}
	}
}
