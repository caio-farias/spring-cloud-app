package com.av3.springcloudappuserinfo.services.movies;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Movie {

  private Integer id;
  private String title;
  private String description;
  private String trailer;
  private String banner;

  @JsonIgnore
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
