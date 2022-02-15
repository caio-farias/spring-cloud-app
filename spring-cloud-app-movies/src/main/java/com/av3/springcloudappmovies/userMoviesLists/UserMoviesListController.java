package com.av3.springcloudappmovies.userMoviesLists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "lists")
public class UserMoviesListController {

  @Autowired
  private UserMoviesListService userMoviesListService;

  @PostMapping(path = "{userId}")
  public UserMoviesList createUserMoviesList(
      @PathVariable("userId") Integer userId,
      @RequestBody UserMoviesList userMoviesList) {
    return userMoviesListService.createUserMoviesList(userId, userMoviesList);
  }

  @GetMapping(path = "{userId}")
  public UserMoviesList getUserMoviesListByUserId(
      @PathVariable("userId") Integer userId) {
    return userMoviesListService.getUserListByUserId(userId);
  }

  @PutMapping(path = "{userId}/add")
  public UserMoviesList addMoviesToUserMoviesList(
      @PathVariable("userId") Integer userId,
      @RequestBody ChangeUserMoviesListDTO changeUserMoviesListDTO) {
    return userMoviesListService.addMovies(userId, changeUserMoviesListDTO.moviesId);
  }

  @PutMapping(path = "{userId}/remove")
  public UserMoviesList removeMoviesFromUserMoviesList(
      @PathVariable("userId") Integer userId,
      @RequestBody ChangeUserMoviesListDTO changeUserMoviesListDTO) {
    return userMoviesListService.removeMovies(userId, changeUserMoviesListDTO.moviesId);
  }

  @PatchMapping(path = "{userId}")
  public UserMoviesList updateUserMoviesListByUserId(
      @PathVariable("userId") Integer userId,
      @RequestBody UserMoviesList userMoviesList) {
    return userMoviesListService.updateMovie(userId, userMoviesList);
  }

  @DeleteMapping(path = "{userId}")
  public void deleteUserMoviesListByUserId(
      @PathVariable("userId") Integer userId) {
    userMoviesListService.deleteUserMoviesList(userId);
  }
}
