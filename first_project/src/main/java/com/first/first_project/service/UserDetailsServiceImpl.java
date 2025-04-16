package com.first.first_project.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.first.first_project.Entity.UserEntity;
import com.first.first_project.repo.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity user = userRepository.findByEmail(email);
    if(user == null){
      throw new UsernameNotFoundException("User not found with email: " + email);
    }
    return new  org.springframework.security.core.userdetails.User(user.getEmail() ,user.getPassword(),new ArrayList<>());
  }
  
}
