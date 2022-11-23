package com.example.myapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapi.exception.ResourceNotFoundException;
import com.example.myapi.model.User;
import com.example.myapi.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
        throws ResourceNotFoundException {
        User User = userRepository.findById(userId)
          .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(User);
    }
    
    @PostMapping("/user")
    public User createUser(@RequestBody User User) {
        return userRepository.save(User);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long UserId,
         @RequestBody User UserDetails) throws ResourceNotFoundException {
        User User = userRepository.findById(UserId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + UserId));

        User.setPassword(UserDetails.getPassword());
        User.setUsername(UserDetails.getUsername());
        final User updatedUser = userRepository.save(User);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/User/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
         throws ResourceNotFoundException {
        User User = userRepository.findById(userId)
       .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(User);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}