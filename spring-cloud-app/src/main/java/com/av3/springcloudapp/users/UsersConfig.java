package com.av3.springcloudapp.users;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UsersConfig {

  @Bean
  CommandLineRunner cmdLineRunner(UsersRepository repository) {
    return args -> {
      User user = new User(
          "caio",
          new BCryptPasswordEncoder().encode("123"));
      repository.saveAll(List.of(user));
    };
  }

}
