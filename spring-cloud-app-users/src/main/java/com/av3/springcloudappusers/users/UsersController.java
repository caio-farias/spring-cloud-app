package com.av3.springcloudappusers.users;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "users")
public class UsersController {
  private final UsersService usersService;

  @Autowired
  public UsersController(UsersService usersService) {
    this.usersService = usersService;
  }

  @GetMapping(path = "{userId}")
  public User getUserById(
      @PathVariable("userId") Integer userId) {
    return usersService.getUserById(userId);
  }

  @GetMapping
  @JsonIgnoreProperties("password")
  public List<User> getUsers() {
    return usersService.getUsers();
  }

  @PostMapping
  public User registerUser(@RequestBody User user) {
    return usersService.createUser(user);
  }

  @PatchMapping(path = "{userId}")
  public User updateUser(
      @PathVariable("userId") Integer userId,
      @RequestParam(required = false) String firstName,
      @RequestParam(required = false) String lastName,
      @RequestParam(required = false) String email,
      @RequestParam(required = false) String username,
      @RequestParam(required = false) String birthDate) {
    return usersService.updateUser(userId, firstName, lastName, birthDate, email, username);
  }

  @DeleteMapping(path = "{userId}")
  public void deleteUSer(
      @PathVariable("userId") Integer userId) {
    usersService.deleteUser(userId);
  }
}
