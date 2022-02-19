package com.av3.springcloudappuserinfo.services.movies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient("${microservices.movies.name}${microservices.movies.context-path}")
public interface MoviesServiceInterface {

  @CircuitBreaker(name = "circuitbreakerDefault", fallbackMethod = "fallback")
  @Retry(name = "retryDefault", fallbackMethod = "retryFallback")
  @Bulkhead(name = "bulkheadDefault", fallbackMethod = "fallback")
  @RequestMapping(method = RequestMethod.GET, value = "${microservices.movies.endpoint}/{userId}", consumes = "application/json")
  UserMoviesList getUserMoviesListByUserId(@PathVariable("userId") Integer userId);

  public default ResponseEntity<String> fallback(Throwable t) {
    return ResponseEntity.ok("FALLBACK(MOVIES) RUNNING: " + t.getMessage());
  }

  public default ResponseEntity<String> retryFallback(Throwable t) {
    return ResponseEntity.ok("FALLBACK(MOVIES) RUNNING FOR RETRY!!");
  }
}