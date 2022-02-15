package com.av3.springcloudapptasks.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.av3.springcloudapptasks.userTasksLists.UserTasksList;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tasks")
public class Task {
  @Id
  @SequenceGenerator(name = "tasks_sequence", sequenceName = "tasks_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_sequence")
  @Column(name = "task_id")
  private Integer id;

  private final static SimpleDateFormat formatBirthDate = new SimpleDateFormat("dd/MM/yyyy");

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private Integer priority;

  @Column(nullable = false)
  private Date deadline;

  @JsonIgnore
  @ManyToMany(mappedBy = "tasks")
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
