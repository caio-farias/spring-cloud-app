package com.av3.springcloudappusersalpha.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${betaservice.endpoint}")
public class UsersController {

  @Autowired
  UsersServiceInterface usersServiceInterface;

  @GetMapping
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok(usersServiceInterface.getUsers());
  }

  @GetMapping("{userId}")
  public ResponseEntity<User> getUserById(
      @PathVariable("userId") Integer userId) {
    return ResponseEntity.ok(usersServiceInterface.getUserById(userId));
  }

  @PostMapping
  public ResponseEntity<User> registerUser(
      @RequestBody User user) {
    return ResponseEntity.ok(usersServiceInterface.registerUser(user));
  }

  @PatchMapping
  public ResponseEntity<User> updateUser(
      @PathVariable("userId") Integer userId,
      @RequestParam(required = false) String firstName,
      @RequestParam(required = false) String lastName,
      @RequestParam(required = false) String email,
      @RequestParam(required = false) String username,
      @RequestParam(required = false) String birthDate) {
    return ResponseEntity.ok(usersServiceInterface.updateUser(userId, firstName,
        lastName, email, username, birthDate));
  }

  @DeleteMapping("{userId}")
  public void deleteUser(
      @PathVariable("userId") Integer userId) {
    usersServiceInterface.deleteUser(userId);
  }
}