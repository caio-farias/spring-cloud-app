package com.av3.springcloudapptasks.tasks;

import java.util.List;
import java.util.Objects;

import com.av3.springcloudapptasks.common.status.ForbiddenStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TasksService {

  @Autowired
  private TasksRepository tasksRepository;

  public Task getTaskById(Integer taskId) {
    return tasksRepository.findById(taskId)
        .orElseThrow(() -> new ForbiddenStatus("Task does not exists."));
  }

  public List<Task> getAllById(List<Integer> ids) {
    return tasksRepository.findAllById(ids);
  }

  public List<Task> getTasks() {
    return tasksRepository.findAll();
  }

  public Task createTask(Task task) {
    boolean sameTask = tasksRepository.findByTitle(task.getTitle()).isPresent();
    if (sameTask)
      throw new ForbiddenStatus("Task already exist.");
    return tasksRepository.save(task);
  }

  @Transactional
  public Task updateTask(Integer taskId, String title, String description, String deadline, Integer priority) {
    Task task = tasksRepository.findById(taskId)
        .orElseThrow(() -> new ForbiddenStatus("Task does not exist."));

    if (title != null
        && title.length() > 0
        && !Objects.equals(title, task.getTitle()))
      task.getTitle();

    if (description != null
        && description.length() > 0
        && !Objects.equals(description, task.getDescription()))
      task.setDescription(description);

    if (deadline != null
        && deadline.length() > 4
        && !Objects.equals(deadline, task.getDeadline()))
      task.setDeadline(deadline);

    if (priority != null && !Objects.equals(priority, task.getPriority()))
      task.setPriority(priority);

    return task;
  }

  public void deleteTask(Integer taskId) {
    boolean exists = tasksRepository.existsById(taskId);
    if (!exists)
      throw new ForbiddenStatus("Task does not exist.");
    tasksRepository.deleteById(taskId);
  }

}
