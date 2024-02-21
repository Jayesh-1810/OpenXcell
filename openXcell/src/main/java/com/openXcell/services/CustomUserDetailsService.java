package com.openXcell.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

import com.openXcell.dao.UsersRepository;
import com.openXcell.model.UserDetailsImpl;

@Component
public class CustomUserDetailsService implements UserDetailsService{
  @Autowired
  private UsersRepository repository;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    com.openXcell.model.User user = repository.findByEmail(username);
    if(user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return UserDetailsImpl.build(user);
  }
}