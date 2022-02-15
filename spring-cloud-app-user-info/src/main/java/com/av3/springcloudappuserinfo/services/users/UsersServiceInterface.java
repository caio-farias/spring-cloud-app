package com.av3.springcloudappuserinfo.services.users;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("${microservices.users.name}${microservices.users.context-path}")
public interface UsersServiceInterface {

  @RequestMapping(method = RequestMethod.GET, value = "${microservices.users.endpoint}/{userId}", consumes = "application/json")
  User getUserById(@PathVariable("userId") Integer userId);

}
