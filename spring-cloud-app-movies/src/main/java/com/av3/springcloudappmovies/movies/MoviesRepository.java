package com.av3.springcloudappmovies.movies;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer> {
  Optional<Movie> findByTitle(String title);

  @Override
  default List<Movie> findAllById(Iterable<Integer> ids) {

    List<Movie> results = new ArrayList<Movie>();

    for (Integer id : ids) {
      findById(id).ifPresent(results::add);
    }

    return results;

  }
}
