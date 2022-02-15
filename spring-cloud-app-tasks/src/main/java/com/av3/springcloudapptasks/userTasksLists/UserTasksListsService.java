package com.av3.springcloudapptasks.userTasksLists;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import com.av3.springcloudapptasks.common.status.ForbiddenStatus;
import com.av3.springcloudapptasks.tasks.Task;
import com.av3.springcloudapptasks.tasks.TasksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTasksListsService {
  @Autowired
  private UserTasksListsRepository userTasksListsRepository;
  @Autowired
  private TasksService tasksService;

  public UserTasksList createUserTasksList(Integer userId, UserTasksList userTasksLists) {
    boolean sameUserId = userTasksListsRepository.findByUserId(userId).isPresent();
    if (sameUserId)
      throw new ForbiddenStatus("This user task list already exist.");
    userTasksLists.setUserId(userId);
    return userTasksListsRepository.save(userTasksLists);
  }

  public UserTasksList getUserListByUserId(Integer userId) {
    return userTasksListsRepository.findByUserId(userId)
        .orElseThrow(() -> new ForbiddenStatus("User task list dont exists."));
  }

  public List<UserTasksList> getUserTasksLists() {
    return userTasksListsRepository.findAll();
  }

  public void deleteUserTasksList(Integer userTasksListsId) {
    boolean exists = userTasksListsRepository.existsById(userTasksListsId);
    if (!exists)
      throw new ForbiddenStatus("User task list does not exist.");
    userTasksListsRepository.deleteById(userTasksListsId);
  }

  @Transactional
  public UserTasksList updateTask(Integer userId, UserTasksList updateUserTasksList) {
    UserTasksList userTasksLists = getUserListByUserId(userId);
    if (userTasksLists.equals(null))
      throw new ForbiddenStatus("User task list does not exist.");

    if (updateUserTasksList.getTitle() != null
        && updateUserTasksList.getTitle().length() > 0
        && !Objects.equals(updateUserTasksList.getTitle(), userTasksLists.getTitle()))
      userTasksLists.setTitle(updateUserTasksList.getTitle());

    return userTasksLists;
  }

  @Transactional
  public UserTasksList addTasks(Integer userId, List<Integer> tasksId) {
    UserTasksList userTasksLists = getUserListByUserId(userId);
    if (userTasksLists.equals(null))
      throw new ForbiddenStatus("User task list does not exist.");

    if (userTasksLists.getTasks() != null) {
      List<Task> tasks = tasksService.getAllById(tasksId);
      userTasksLists.addTasks(tasks);
    }

    return userTasksListsRepository.save(userTasksLists);
  }

  @Transactional
  public UserTasksList removeTasks(Integer userId, List<Integer> tasksId) {
    UserTasksList userTasksLists = getUserListByUserId(userId);
    if (userTasksLists.equals(null))
      throw new ForbiddenStatus("User task list does not exist.");

    if (userTasksLists.getTasks() != null
        && userTasksLists.getTasks().size() > 0) {
      List<Task> tasks = tasksService.getAllById(tasksId);
      userTasksLists.removeTasks(tasks);
    }
    return userTasksListsRepository.save(userTasksLists);
  }

}
