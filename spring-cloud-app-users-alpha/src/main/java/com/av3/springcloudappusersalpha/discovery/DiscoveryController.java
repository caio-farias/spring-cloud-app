package com.av3.springcloudappusersalpha.discovery;

import com.av3.springcloudappusersalpha.users.UsersServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class DiscoveryController {

  @Autowired
  UsersServiceInterface usersServiceInterface;

  @GetMapping
  public ResponseEntity<String> ping() {
    return ResponseEntity.ok(usersServiceInterface.ping());
  }

}
