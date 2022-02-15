package com.av3.springcloudappuserinfo.services.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Task {

  private Integer id;

  private final static SimpleDateFormat formatBirthDate = new SimpleDateFormat("dd/MM/yyyy");

  private String title;

  private String description;

  private Integer priority;

  private Date deadline;

  @JsonIgnore
  Set<UserTasksList> taskList = new HashSet<>();

  public Task() {
  }

  public Task(Integer id, String title, String description, Integer priority, String deadline) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.setPriority(priority);
    this.setDeadline(deadline);
  }

  @Override
  public String toString() {
    return "{" +
        " id ='" + getId() + "'" +
        ", title='" + getTitle() + "'" +
        ", description='" + getDescription() + "'" +
        ", deadline='" + getDeadline() + "'" +
        ", priority='" + getPriority() + "'" +
        "}";
  }

  public String getDeadline() {
    if (deadline == null)
      return null;
    return formatBirthDate.format(deadline).toString();
  }

  public void setDeadline(String deadline) {
    try {
      this.deadline = formatBirthDate.parse(deadline);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public Integer getId() {
    return id;
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
