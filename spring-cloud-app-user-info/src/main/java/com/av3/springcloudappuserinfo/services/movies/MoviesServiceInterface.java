package com.av3.springcloudappuserinfo.services.movies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("${microservices.movies.name}${microservices.movies.context-path}")
public interface MoviesServiceInterface {

  @RequestMapping(method = RequestMethod.GET, value = "${microservices.movies.endpoint}/{userId}", consumes = "application/json")
  UserMoviesList getUserMoviesListByUserId(@PathVariable("userId") Integer userId);
}