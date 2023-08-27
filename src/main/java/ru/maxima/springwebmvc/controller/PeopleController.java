package ru.maxima.springwebmvc.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maxima.springwebmvc.dao.PersonDAO;
import ru.maxima.springwebmvc.entity.Person;

import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    // private final PersonValidator personValidator;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
        // this.personValidator = personValidator;
    }

    @GetMapping()
    public String getAllPersons(Model model) {
        model.addAttribute("people", personDAO.getAllPersons());
        return "people/index";
    }

    @GetMapping("/{id}")
    private String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "/people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person) {

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PostMapping("/{id}")
    private String update(@ModelAttribute("person") @Valid Person person,
                          @PathVariable("id") int id) {
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

   @GetMapping("/search-name")
    public String findByName(Model model, @RequestParam("keyword") String keyword) {
       List<Person> searchResult;
        if (keyword != null && !keyword.isEmpty()) {
           searchResult = personDAO.findByName(keyword);
       } else {
           searchResult = Collections.emptyList();
       }
       model.addAttribute("searchResult", searchResult);
       model.addAttribute("keyword", keyword);
       return "people/search-name";
    }

    @GetMapping("/search-id")
    public String findById(Model model, @RequestParam("id") int id) {
        List<Person> searchResult;
        if (id > 0) {
            searchResult = personDAO.findById(id);
        } else {
            searchResult = Collections.emptyList();
        }
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("id", id);
        return "people/search-id";
    }

}



