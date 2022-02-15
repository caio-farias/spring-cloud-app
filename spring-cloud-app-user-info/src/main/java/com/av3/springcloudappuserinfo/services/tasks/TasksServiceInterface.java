package com.av3.springcloudappuserinfo.services.tasks;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("${microservices.tasks.name}${microservices.tasks.context-path}")
public interface TasksServiceInterface {

  @RequestMapping(method = RequestMethod.GET, value = "${microservices.tasks.endpoint}/{userId}", consumes = "application/json")
  UserTasksList getUserTasksListByUserId(@PathVariable("userId") Integer userId);
}