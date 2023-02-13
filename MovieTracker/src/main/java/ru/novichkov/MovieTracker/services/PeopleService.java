package ru.novichkov.MovieTracker.services;

import org.hibernate.Hibernate;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.novichkov.MovieTracker.models.Movie;
import ru.novichkov.MovieTracker.models.Person;
import ru.novichkov.MovieTracker.repositories.PeopleRepository;
import ru.novichkov.MovieTracker.security.PersonDetails;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);

        return foundPerson.orElse(null);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        updatedPerson.setUsername(findOne(id).getUsername());
        updatedPerson.setPassword(findOne(id).getPassword());
        peopleRepository.save(updatedPerson);
    }

    public Person goToHomePage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Optional<Person> foundPerson = peopleRepository.findByUsername(personDetails.getUsername());

        return foundPerson.orElse(null);
    }

    public List<Movie> getMyMoviesById() {
        Person person = goToHomePage();
        Hibernate.initialize(person.getMovies());
        return person.getMovies();
    }

}
