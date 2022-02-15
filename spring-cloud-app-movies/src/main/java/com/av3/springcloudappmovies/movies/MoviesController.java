package com.av3.springcloudappmovies.movies;

import java.util.List;

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
@RequestMapping(path = "movies")
public class MoviesController {
  private final MoviesService moviesService;

  @Autowired
  public MoviesController(MoviesService moviesService) {
    this.moviesService = moviesService;
  }

  @GetMapping(path = "{movieId}")
  public Movie getMovieById(
      @PathVariable("movieId") Integer movieId) {
    return moviesService.getMovieById(movieId);
  }

  @GetMapping
  public List<Movie> getMovies() {
    return moviesService.getMovies();
  }

  @PostMapping
  public Movie registerMovie(@RequestBody Movie movie) {
    return moviesService.createMovie(movie);
  }

  @PatchMapping(path = "{movieId}")
  public Movie updateUser(
      @PathVariable("movieId") Integer movieId,
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String description,
      @RequestParam(required = false) String trailer,
      @RequestParam(required = false) String banner) {
    return moviesService.updateMovie(movieId, title, description, trailer, banner);
  }

  @DeleteMapping(path = "{movieId}")
  public void deleteUser(
      @PathVariable("movieId") Integer movieId) {
    moviesService.deleteMovie(movieId);
  }
}
