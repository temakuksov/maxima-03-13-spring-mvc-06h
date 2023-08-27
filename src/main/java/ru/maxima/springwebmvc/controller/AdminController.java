package ru.maxima.springwebmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maxima.springwebmvc.dao.PersonDAO;
import ru.maxima.springwebmvc.entity.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PersonDAO personDAO;
    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String getForm(Model model, @ModelAttribute("person")Person person) {
        model.addAttribute("allPeople", personDAO.getAllPersons());
        return "people/adminPage";
    }

    @PostMapping("/add")
    public String makeAdmin(@ModelAttribute("person")Person person) {
        Person founded = personDAO.show(person.getId());
        System.out.println(founded.getName());
        System.out.println(founded.getAge());
        System.out.println(founded.getEmail());
        return "redirect:/people";
    }
}
