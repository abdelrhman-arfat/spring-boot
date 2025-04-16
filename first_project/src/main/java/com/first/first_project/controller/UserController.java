package com.first.first_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first.first_project.Entity.UserEntity;
import com.first.first_project.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/signup")
  public UserEntity userSignUp(@RequestBody UserEntity user) {
    return userService.userSignUp(user);
  }

  @PostMapping("/login")
  public UserEntity userLogin(@RequestBody UserEntity user) {
    return userService.userLogin(user);
  }

  @PutMapping("/{email}")
  public UserEntity updateUser(@PathVariable("email") String email, @RequestBody UserEntity user) {
    return userService.updateUser(email, user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable("id") Long id) {
    userService.deleteUser(id);
  }
}