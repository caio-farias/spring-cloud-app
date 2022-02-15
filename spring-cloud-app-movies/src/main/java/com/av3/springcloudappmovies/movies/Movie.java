package com.av3.springcloudappmovies.movies;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.av3.springcloudappmovies.userMoviesLists.UserMoviesList;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "movies")
public class Movie {
  @Id
  @SequenceGenerator(name = "movies_sequence", sequenceName = "movies_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movies_sequence")
  @Column(name = "movie_id")
  private Integer id;
  private String title;
  private String description;
  private String trailer;
  private String banner;

  @JsonIgnore
  @ManyToMany(mappedBy = "movies")
  Set<UserMoviesList> movieOnUserMovieList = new HashSet<>();

  public Movie() {
  }

  public Movie(Integer id, String title, String description, String trailer, String banner) {
    this.id = id;
    setTitle(title);
    setDescription(description);
    setTrailer(trailer);
    setBanner(banner);
  }

  @Override
  public String toString() {
    return "{" +
        " id ='" + getId() + "'" +
        ", title='" + getTitle() + "'" +
        ", description='" + getDescription() + "'" +
        ", trailer='" + getTrailer() + "'" +
        ", banner='" + getBanner() + "'" +
        "}";
  }

  public Integer getId() {
    return id;
  }

  public String getBanner() {
    return banner;
  }

  public void setBanner(String banner) {
    this.banner = banner;
  }

  public String getTrailer() {
    return trailer;
  }

  public void setTrailer(String trailer) {
    this.trailer = trailer;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
