package ru.novichkov.MovieTracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.novichkov.MovieTracker.models.Movie;
@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer> {
}
