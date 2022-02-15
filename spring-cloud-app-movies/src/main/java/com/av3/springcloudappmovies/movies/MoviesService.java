package com.av3.springcloudappmovies.movies;

import java.util.List;
import java.util.Objects;

import com.av3.springcloudappmovies.common.status.ForbiddenStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MoviesService {

  @Autowired
  private MoviesRepository moviesRepository;

  public Movie getMovieById(Integer movieId) {
    return moviesRepository.findById(movieId)
        .orElseThrow(() -> new ForbiddenStatus("Movie does not exists."));
  }

  public List<Movie> getAllById(List<Integer> ids) {
    return moviesRepository.findAllById(ids);
  }

  public List<Movie> getMovies() {
    return moviesRepository.findAll();
  }

  public Movie createMovie(Movie movie) {
    boolean sameMovie = moviesRepository.findByTitle(movie.getTitle()).isPresent();
    if (sameMovie)
      throw new ForbiddenStatus("Movie already exist.");
    return moviesRepository.save(movie);
  }

  @Transactional
  public Movie updateMovie(Integer movieId, String title, String description, String trailer, String banner) {
    Movie movie = moviesRepository.findById(movieId)
        .orElseThrow(() -> new ForbiddenStatus("Movie does not exist."));

    if (title != null
        && title.length() > 0
        && !Objects.equals(title, movie.getTitle()))
      movie.getTitle();

    if (description != null
        && description.length() > 0
        && !Objects.equals(description, movie.getDescription()))
      movie.setDescription(description);

    if (trailer != null
        && trailer.length() > 0
        && !Objects.equals(trailer, movie.getTrailer()))
      movie.setTrailer(trailer);

    if (banner != null
        && banner.length() > 4
        && !Objects.equals(banner, movie.getBanner()))
      movie.setBanner(banner);

    return movie;
  }

  public void deleteMovie(Integer movieId) {
    boolean exists = moviesRepository.existsById(movieId);
    if (!exists)
      throw new ForbiddenStatus("Movie does not exist.");
    moviesRepository.deleteById(movieId);
  }

}
