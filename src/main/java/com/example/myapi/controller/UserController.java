package com.example.myapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapi.exception.ResourceNotFoundException;
import com.example.myapi.model.User;
import com.example.myapi.repository.UserRepository;

@RestController
@RequestMapping()
public class UserController {

	@Autowired private UserRepository userRepository;
	
	@PostMapping("/user")
	public ResponseEntity<User> create(@RequestBody @Valid User user) {
		User savedUser = userRepository.save(user);
		URI UserURI = URI.create("/user/" + savedUser.getId());
		return ResponseEntity.created(UserURI).body(savedUser);
	}
	
	@GetMapping("/user")
	public List<User> list() {
		return userRepository.findAll();
	}
		
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id)
        throws ResourceNotFoundException {
        User User = userRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        return ResponseEntity.ok().body(User);
		}
}