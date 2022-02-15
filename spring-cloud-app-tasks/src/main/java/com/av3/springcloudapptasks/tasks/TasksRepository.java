package com.av3.springcloudapptasks.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Task, Integer> {
  Optional<Task> findByTitle(String title);

  @Override
  default List<Task> findAllById(Iterable<Integer> ids) {

    List<Task> results = new ArrayList<Task>();

    for (Integer id : ids) {
      findById(id).ifPresent(results::add);
    }

    return results;

  }
}
