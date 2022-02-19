package com.av3.springcloudappuserinfo.services.users;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient("${microservices.users.name}${microservices.users.context-path}")
public interface UsersServiceInterface {

  @CircuitBreaker(name = "circuitbreakerDefault", fallbackMethod = "fallback")
  @Retry(name = "retryDefault", fallbackMethod = "retryFallback")
  @Bulkhead(name = "bulkheadDefault", fallbackMethod = "fallback")
  @RequestMapping(method = RequestMethod.GET, value = "${microservices.users.endpoint}/{userId}", consumes = "application/json")
  User getUserById(@PathVariable("userId") Integer userId);

  public default ResponseEntity<String> fallback(Throwable t) {
    return ResponseEntity.ok("FALLBACK(USERS) RUNNING: " + t.getMessage());
  }

  public default ResponseEntity<String> retryFallback(Throwable t) {
    return ResponseEntity.ok("FALLBACK(USERS) RUNNING FOR RETRY!!");
  }
}
