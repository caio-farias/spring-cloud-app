package com.av3.springcloudappuserinfo.userInfo;

import com.av3.springcloudappuserinfo.services.movies.MoviesServiceInterface;
import com.av3.springcloudappuserinfo.services.movies.UserMoviesList;
import com.av3.springcloudappuserinfo.services.tasks.TasksServiceInterface;
import com.av3.springcloudappuserinfo.services.tasks.UserTasksList;
import com.av3.springcloudappuserinfo.services.users.User;
import com.av3.springcloudappuserinfo.services.users.UsersServiceInterface;
import com.av3.springcloudappuserinfo.status.BadRequestStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user-info")
public class UserInfoController {

  @Autowired
  UsersServiceInterface usersServiceInterface;

  @Autowired
  MoviesServiceInterface moviesServiceInterface;

  @Autowired
  TasksServiceInterface tasksServiceInterface;

  @GetMapping(path = "{userId}")
  public ResponseEntity<UserInfo> getAllUserInfo(
      @PathVariable("userId") Integer userId) {
    UserInfo userInfo = new UserInfo();
    try {
      User user = usersServiceInterface.getUserById(userId);
      if (user.equals(null))
        throw new BadRequestStatus("This user does not exist.");
      userInfo.setUser(user);
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      UserTasksList userTasksList = tasksServiceInterface.getUserTasksListByUserId(userId);
      if (!userTasksList.equals(null))
        userInfo.setTasksList(userTasksList);
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      UserMoviesList userMoviesList = moviesServiceInterface.getUserMoviesListByUserId(userId);
      if (!userMoviesList.equals(null))
        userInfo.setMoviesList(userMoviesList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok(userInfo);
  }

}