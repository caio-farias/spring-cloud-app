package com.av3.springcloudappuserinfo.services.movies;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserMoviesList {
  private Integer id;
  private Integer userId;
  private String title;

  private Set<Movie> movies = new HashSet<>();

  @Override
  public String toString() {
    return "{" +
        " id ='" + getId() + "'" +
        ", userId='" + getUserId() + "'" +
        ", title='" + getTitle() + "'" +
        ", movies='" + getMovies() + "'" +
        "}";
  }

  public Integer getId() {
    return id;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getUserId() {
    return userId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Set<Movie> getMovies() {
    return movies;
  }

  public void addMovies(List<Movie> newMovies) {
    getMovies().addAll(newMovies);
  }

  public void removeMovies(List<Movie> removedMovies) {
    getMovies().removeAll(removedMovies);
  }

}
