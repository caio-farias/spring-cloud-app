package com.av3.springcloudappmovies.userMoviesLists;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMoviesListRepository extends JpaRepository<UserMoviesList, Integer> {
  Optional<UserMoviesList> findByUserId(Integer userId);

  Optional<UserMoviesList> findByTitle(String title);
}
