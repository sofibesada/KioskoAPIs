package com.uade.tpo.Marketplace.controllers.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.uade.tpo.Marketplace.repository.users.UserRepository;

import lombok.RequiredArgsConstructor;

@Component("userSecurity")
@RequiredArgsConstructor
public class UserSecurity {
 
  private final UserRepository userRepository;
 
  public boolean isSelf(Long pathUserId, Authentication authentication) {
    String email = authentication.getName();
    return userRepository.findByEmail(email)
        .map(u -> u.getId().equals(pathUserId))
        .orElse(false);
  }
}
