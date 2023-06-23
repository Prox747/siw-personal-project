package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GeneralController {
    @Autowired
    private UserService userService;
    @Autowired
    private CredentialsService credentialsService;

    @ModelAttribute("currentUser")
    public User getUser() {
        return userService.getCurrentUser();
    }
    
    @ModelAttribute("userIsRecruiter")
    public boolean userIsRecruiter() {
        if(userService.getCurrentUser() == null) return false;
        return credentialsService.userIsRecruiter();
    }

    @ModelAttribute("userIsApplicant")
    public boolean userIsApplicant() {
        if(userService.getCurrentUser() == null) return false;
        return credentialsService.userIsApplicant();
    }

    @ModelAttribute("userIsRegistered")
    public boolean userIsRegistered() {
        return userService.getCurrentUser() != null;
    }

    @ModelAttribute("myErrorClass")
    public String getMyErrorClassForBootstrap() {
        return "alert alert-danger py-2 w-fit-content m-2";
    }
}