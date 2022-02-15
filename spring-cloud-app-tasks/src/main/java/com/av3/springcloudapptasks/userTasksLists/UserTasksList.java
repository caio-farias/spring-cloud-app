package com.av3.springcloudapptasks.userTasksLists;

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

import com.av3.springcloudapptasks.tasks.Task;

@Entity
@Table(name = "user_tasks_list", uniqueConstraints = { @UniqueConstraint(columnNames = { "userId" }) })
public class UserTasksList {
  @Id
  @SequenceGenerator(name = "user_tasks_list_sequence", sequenceName = "user_tasks_list_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_tasks_list_sequence")
  @Column(name = "user_tasks_list_id")
  private Integer id;
  private Integer userId;
  private String title;

  @ManyToMany()
  @JoinTable(name = "user_tasks_list_has_tasks", joinColumns = @JoinColumn(name = "user_tasks_list_id"), inverseJoinColumns = @JoinColumn(name = "task_id"))
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
