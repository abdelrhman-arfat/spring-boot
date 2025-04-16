package com.first.first_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.first.first_project.Entity.UserEntity;
import com.first.first_project.repo.UserRepository;

@Service
public class UserService {
  @Autowired
  private  PasswordEncoder passwordEncoder;
  @Autowired
  private UserRepository userRepository;


  public UserEntity userSignUp(UserEntity user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }
  public UserEntity userLogin(UserEntity LoginUser) {
    UserEntity existingUser = userRepository.findByEmail(LoginUser.getEmail());
    if (existingUser == null) {
      throw new RuntimeException("User not found");
    }
    if (!existingUser.getPassword().equals(LoginUser.getPassword())) {
      throw new RuntimeException("Invalid password");
    }
    return existingUser ;
  }

  public UserEntity updateUser(String email, UserEntity user) {
    UserEntity existingUser = userRepository.findByEmail(email);
    if (existingUser == null) {
      throw new RuntimeException("User not found");
    }
    existingUser.setFirst_name(user.getFirst_name());
    existingUser.setLast_name(user.getLast_name());
    existingUser.setEmail(user.getEmail());
    existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
    existingUser.setRole(user.getRole());
    return userRepository.save(existingUser);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  // public void userLogout(UserEntity user) {
    
  // }

  
}
