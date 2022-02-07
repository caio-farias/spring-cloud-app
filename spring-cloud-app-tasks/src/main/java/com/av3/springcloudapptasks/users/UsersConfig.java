package com.av3.springcloudapptasks.users;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UsersConfig {

  @Bean
  CommandLineRunner cmdLineRunner(UsersRepository repository) {
    return args -> {
      User user = new User(
          "caio",
          passwordEncoder().encode("123"));
      repository.saveAll(List.of(user));
    };
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
