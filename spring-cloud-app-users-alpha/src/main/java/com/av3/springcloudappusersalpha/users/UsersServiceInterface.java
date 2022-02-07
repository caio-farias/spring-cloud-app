package com.av3.springcloudappusersalpha.users;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("${betaservice.name}${betaservice.context-path}")
public interface UsersServiceInterface {

  @RequestMapping(method = RequestMethod.GET, value = "/", consumes = "application/json")
  String ping();

  @RequestMapping(method = RequestMethod.GET, value = "${betaservice.endpoint}", consumes = "application/json")
  List<User> getUsers();

  @RequestMapping(method = RequestMethod.GET, value = "${betaservice.endpoint}/{usersId}", consumes = "application/json")
  User getUserById(@PathVariable("userId") Integer userId);

  @RequestMapping(method = RequestMethod.POST, value = "${betaservice.endpoint}", consumes = "application/json")
  User registerUser(@RequestBody User user);

  @RequestMapping(method = RequestMethod.PATCH, value = "${betaservice.endpoint}/{userId}", consumes = "application/json")
  User updateUser(
      @PathVariable("userId") Integer userId,
      @RequestParam(required = false) String firstName,
      @RequestParam(required = false) String lastName,
      @RequestParam(required = false) String email,
      @RequestParam(required = false) String username,
      @RequestParam(required = false) String birthDate);

  @RequestMapping(method = RequestMethod.DELETE, value = "${betaservice.endpoint}/{usersId}", consumes = "application/json")
  void deleteUser(@PathVariable("userId") Integer userId);

}
