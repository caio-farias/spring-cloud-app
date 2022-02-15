package com.av3.springcloudappmovies.userMoviesLists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.av3.springcloudappmovies.movies.Movie;

@Entity
@Table(name = "user_movies_list", uniqueConstraints = { @UniqueConstraint(columnNames = { "userId" }) })
public class UserMoviesList {
  @Id
  @SequenceGenerator(name = "user_movies_list_sequence", sequenceName = "user_movies_list_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_movies_list_sequence")
  @Column(name = "user_movies_list_id")
  private Integer id;
  private Integer userId;
  private String title;

  @ManyToMany()
  @JoinTable(name = "user_movies_list_has_movies", joinColumns = @JoinColumn(name = "user_movies_list_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
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
