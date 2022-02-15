package com.av3.springcloudapptasks.userTasksLists;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTasksListsRepository extends JpaRepository<UserTasksList, Integer> {
  Optional<UserTasksList> findByUserId(Integer userId);

  Optional<UserTasksList> findByTitle(String title);
}
