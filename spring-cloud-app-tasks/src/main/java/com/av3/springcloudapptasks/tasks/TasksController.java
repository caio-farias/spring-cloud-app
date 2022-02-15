package com.av3.springcloudapptasks.tasks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "tasks")
public class TasksController {
  private final TasksService tasksService;

  @Autowired
  public TasksController(TasksService tasksService) {
    this.tasksService = tasksService;
  }

  @GetMapping(path = "{taskId}")
  public Task getTaskById(
      @PathVariable("taskId") Integer taskId) {
    return tasksService.getTaskById(taskId);
  }

  @GetMapping
  public List<Task> getTasks() {
    return tasksService.getTasks();
  }

  @PostMapping
  public Task registerTask(@RequestBody Task task) {
    return tasksService.createTask(task);
  }

  @PatchMapping(path = "{taskId}")
  public Task updateUser(
      @PathVariable("taskId") Integer taskId,
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String description,
      @RequestParam(required = false) String deadline,
      @RequestParam(required = false) Integer priority) {
    return tasksService.updateTask(taskId, title, description, deadline, priority);
  }

  @DeleteMapping(path = "{taskId}")
  public void deleteUser(
      @PathVariable("taskId") Integer taskId) {
    tasksService.deleteTask(taskId);
  }
}
