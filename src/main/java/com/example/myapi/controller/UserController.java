package com.example.myapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapi.model.User;
import com.example.myapi.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired private UserRepository repo;
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody @Valid User user) {
		User savedUser = repo.save(user);
		URI UserURI = URI.create("/user/" + savedUser.getId());
		return ResponseEntity.created(UserURI).body(savedUser);
	}
	
	@GetMapping
	public List<User> list() {
		return repo.findAll();
	}
}