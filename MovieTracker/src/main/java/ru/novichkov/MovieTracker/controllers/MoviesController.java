package ru.novichkov.MovieTracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.novichkov.MovieTracker.models.Movie;
import ru.novichkov.MovieTracker.services.MoviesService;

import javax.validation.Valid;

@Controller
@RequestMapping("/homepage")
public class MoviesController {

    private final MoviesService moviesService;

    @Autowired
    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping("/movies")
    public String findAllMovies(Model model) {
        model.addAttribute("movies", moviesService.findAll());
        return "movies/movies";
    }

    @GetMapping("/myMovies/new")
    public String addMovie(@ModelAttribute("movie") Movie movie) {
        return "movies/new";
    }

    @PostMapping("/myMovies/new")
    public String create(@ModelAttribute("movie") @Valid Movie movie,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "movies/new";

        moviesService.save(movie);
        return "redirect:/homepage/myMovies";
    }
}
