package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GeneralController {
    @Autowired
    private UserService userService;

    @ModelAttribute("currentUser")
    public User getUser() {
        return userService.getCurrentUser();
    }
}