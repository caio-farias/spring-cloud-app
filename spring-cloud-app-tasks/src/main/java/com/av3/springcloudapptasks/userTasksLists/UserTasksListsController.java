package com.av3.springcloudapptasks.userTasksLists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "lists")
public class UserTasksListsController {

  @Autowired
  private UserTasksListsService userTasksListsService;

  @PostMapping(path = "{userId}")
  public UserTasksList createUserTasksList(
      @PathVariable("userId") Integer userId,
      @RequestBody UserTasksList userTasksLists) {
    return userTasksListsService.createUserTasksList(userId, userTasksLists);
  }

  @GetMapping(path = "{userId}")
  public UserTasksList getUserTasksListByUserId(
      @PathVariable("userId") Integer userId) {
    return userTasksListsService.getUserListByUserId(userId);
  }

  @PutMapping(path = "{userId}/add")
  public UserTasksList addTasksToUserTasksList(
      @PathVariable("userId") Integer userId,
      @RequestBody ChangeUserTasksListDTO changeUserTasksListDTO) {
    return userTasksListsService.addTasks(userId, changeUserTasksListDTO.tasksId);
  }

  @PutMapping(path = "{userId}/remove")
  public UserTasksList removeTasksFromUserTasksList(
      @PathVariable("userId") Integer userId,
      @RequestBody ChangeUserTasksListDTO changeUserTasksListDTO) {
    return userTasksListsService.removeTasks(userId, changeUserTasksListDTO.tasksId);
  }

  @PatchMapping(path = "{userId}")
  public UserTasksList updateUserTasksListByUserId(
      @PathVariable("userId") Integer userId,
      @RequestBody UserTasksList userTasksLists) {
    return userTasksListsService.updateTask(userId, userTasksLists);
  }

  @DeleteMapping(path = "{userId}")
  public void deleteUserTasksListByUserId(
      @PathVariable("userId") Integer userId) {
    userTasksListsService.deleteUserTasksList(userId);
  }
}
