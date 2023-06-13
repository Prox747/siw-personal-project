package it.uniroma3.siw.controller;

import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.util.ModelPreparationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class JobAdController {
    @Autowired
    UserService userService;
    @Autowired
    ModelPreparationUtil modelPreparationUtil;

}
