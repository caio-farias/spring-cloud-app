package com.av3.springcloudapp.auth;

import java.util.ArrayList;
import java.util.Collection;

import com.av3.springcloudapp.common.status.ForbiddenStatus;
import com.av3.springcloudapp.users.User;
import com.av3.springcloudapp.users.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService implements UserDetailsService {

  @Autowired
  private final UsersRepository usersRepository;

  @Autowired
  public AuthService(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = usersRepository.findByUsername(username)
        .orElseThrow(() -> new ForbiddenStatus("User does not exists."));

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority("USER"));

    return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(),
        authorities);
  }

}
