package com.av3.springcloudappuserinfo.services.tasks;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserTasksList {

  private Integer id;
  private Integer userId;
  private String title;

  private Set<Task> tasks = new HashSet<>();

  @Override
  public String toString() {
    return "{" +
        " id ='" + getId() + "'" +
        ", userId='" + getUserId() + "'" +
        ", title='" + getTitle() + "'" +
        ", tasks='" + getTasks() + "'" +
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

  public Set<Task> getTasks() {
    return tasks;
  }

  public void addTasks(List<Task> newTasks) {
    getTasks().addAll(newTasks);
  }

  public void removeTasks(List<Task> removedTasks) {
    getTasks().removeAll(removedTasks);
  }

}
