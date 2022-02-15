package com.av3.springcloudappuserinfo.userInfo;

import com.av3.springcloudappuserinfo.services.movies.UserMoviesList;
import com.av3.springcloudappuserinfo.services.tasks.UserTasksList;
import com.av3.springcloudappuserinfo.services.users.User;

public class UserInfo {
  private User user;
  private UserMoviesList moviesList;
  private UserTasksList tasksList;

  public UserInfo() {
  }

  public User getUser() {
    return user;
  }

  public UserTasksList getTasksList() {
    return tasksList;
  }

  public void setTasksList(UserTasksList tasksList) {
    this.tasksList = tasksList;
  }

  public UserMoviesList getMoviesList() {
    return moviesList;
  }

  public void setMoviesList(UserMoviesList moviesList) {
    this.moviesList = moviesList;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
