package com.av3.springcloudappmovies.userMoviesLists;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import com.av3.springcloudappmovies.common.status.ForbiddenStatus;
import com.av3.springcloudappmovies.movies.Movie;
import com.av3.springcloudappmovies.movies.MoviesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMoviesListService {
  @Autowired
  private UserMoviesListRepository userMoviesListRepository;
  @Autowired
  private MoviesService moviesService;

  public UserMoviesList createUserMoviesList(Integer userId, UserMoviesList userMoviesList) {
    boolean sameUserId = userMoviesListRepository.findByUserId(userId).isPresent();
    if (sameUserId)
      throw new ForbiddenStatus("This user movie list already exist.");
    userMoviesList.setUserId(userId);
    return userMoviesListRepository.save(userMoviesList);
  }

  public UserMoviesList getUserListByUserId(Integer userId) {
    return userMoviesListRepository.findByUserId(userId)
        .orElseThrow(() -> new ForbiddenStatus("User movie list dont exists."));
  }

  public List<UserMoviesList> getUserMoviesLists() {
    return userMoviesListRepository.findAll();
  }

  public void deleteUserMoviesList(Integer userMoviesListId) {
    boolean exists = userMoviesListRepository.existsById(userMoviesListId);
    if (!exists)
      throw new ForbiddenStatus("User movie list does not exist.");
    userMoviesListRepository.deleteById(userMoviesListId);
  }

  @Transactional
  public UserMoviesList updateMovie(Integer userId, UserMoviesList updateUserMoviesList) {
    UserMoviesList userMoviesList = getUserListByUserId(userId);
    if (userMoviesList.equals(null))
      throw new ForbiddenStatus("User movie list does not exist.");

    if (updateUserMoviesList.getTitle() != null
        && updateUserMoviesList.getTitle().length() > 0
        && !Objects.equals(updateUserMoviesList.getTitle(), userMoviesList.getTitle()))
      userMoviesList.setTitle(updateUserMoviesList.getTitle());

    return userMoviesList;
  }

  @Transactional
  public UserMoviesList addMovies(Integer userId, List<Integer> moviesId) {
    UserMoviesList userMoviesList = getUserListByUserId(userId);
    if (userMoviesList.equals(null))
      throw new ForbiddenStatus("User movie list does not exist.");

    if (userMoviesList.getMovies() != null) {
      List<Movie> movies = moviesService.getAllById(moviesId);
      userMoviesList.addMovies(movies);
    }

    return userMoviesListRepository.save(userMoviesList);
  }

  @Transactional
  public UserMoviesList removeMovies(Integer userId, List<Integer> moviesId) {
    UserMoviesList userMoviesList = getUserListByUserId(userId);
    if (userMoviesList.equals(null))
      throw new ForbiddenStatus("User movie list does not exist.");

    if (userMoviesList.getMovies() != null
        && userMoviesList.getMovies().size() > 0) {
      List<Movie> movies = moviesService.getAllById(moviesId);
      userMoviesList.removeMovies(movies);
    }
    return userMoviesListRepository.save(userMoviesList);
  }

}
