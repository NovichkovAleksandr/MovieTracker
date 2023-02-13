package ru.novichkov.MovieTracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.novichkov.MovieTracker.models.Movie;
import ru.novichkov.MovieTracker.models.Person;
import ru.novichkov.MovieTracker.repositories.MoviesRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MoviesService {

    private final MoviesRepository moviesRepository;
    private final PeopleService peopleService;

    @Autowired
    public MoviesService(MoviesRepository moviesRepository, PeopleService peopleService) {
        this.moviesRepository = moviesRepository;
        this.peopleService = peopleService;
    }

    public List<Movie> findAll() {
        return moviesRepository.findAll();
    }

    @Transactional
    public void save(Movie movie) {
        movie.setUser(peopleService.goToHomePage());
        moviesRepository.save(movie);
    }
}
