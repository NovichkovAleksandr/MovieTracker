package ru.novichkov.MovieTracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.novichkov.MovieTracker.models.Person;
import ru.novichkov.MovieTracker.services.PeopleService;

@Controller
@RequestMapping("/homepage")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/people")
    public String findAll(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/people";
    }

    @GetMapping("/people/{id}")
    public String showPage(@PathVariable("id") int id, Model model) {
        model.addAttribute(peopleService.findOne(id));
        return "people/show";
    }

    @DeleteMapping("/people/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/homepage/people";
    }

    @GetMapping()
    public String showHomePage(Model model) {
        model.addAttribute(peopleService.goToHomePage());
        return "homepage";
    }

    @GetMapping("/myMovies")
    public String showMyMovies(Model model) {
        model.addAttribute("movies", peopleService.getMyMoviesById());
        return "people/myMovies";
    }

    @PatchMapping("/people/{id}")
    public String update(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id) {
        peopleService.update(id, person);
        return "redirect:/homepage/people";
    }
}
