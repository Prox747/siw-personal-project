package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Company;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.util.FileUploadUtil;
import it.uniroma3.siw.util.ModelPreparationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthenticationController {

    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    UserService userService;
    @Autowired
    ModelPreparationUtil modelPreparationUtil;


    @GetMapping("/registerUser")
    public String showRegisterForm (Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        return "formRegisterUser";
    }

    @GetMapping("/registerCompany")
    public String showRegisterFormCompany (Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        model.addAttribute("company", new Company());
        return "formRegisterCompany";
    }

    @GetMapping("/login")
    public String showLoginForm (Model model) {
        return "formLogin.html";
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index.html";
    }

    @GetMapping("/success")
    public String defaultAfterLogin(Model model) {
        return index(model);
    }

    @PostMapping("/registerUser")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult userBindingResult, @Valid
                               @ModelAttribute("credentials") Credentials credentials,
                               BindingResult credentialsBindingResult,
                               Model model) {

        // se user e credential hanno entrambi contenuti validi, memorizza User e le Credentials nel DB
        if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            model.addAttribute("user", user);
            return "registrationSuccessful";
        }
        return "formRegisterUser";
    }

    //-----------------------------------------------------------------  |
    //CREDENTIALS SALVA USER IN CASCADE E USER SALVA COMPANY IN CASCADE  |
    //-----------------------------------------------------------------  V
    @PostMapping("/registerCompany")
    public String registerCompany(@Valid @ModelAttribute("company") Company company,
                               BindingResult companyBindingResult,
                               @Valid @ModelAttribute("user") User user,
                               BindingResult userBindingResult, @Valid
                               @ModelAttribute("credentials") Credentials credentials,
                               BindingResult credentialsBindingResult,
                               Model model) {

        // se user e credential hanno entrambi contenuti validi, memorizza User e le Credentials nel DB
        if( ! (userBindingResult.hasErrors()
                || credentialsBindingResult.hasErrors()
                || companyBindingResult.hasErrors())) {
            //-----------------------------------------------------------------
            //CREDENTIALS SALVA USER IN CASCADE E USER SALVA COMPANY IN CASCADE
            //-----------------------------------------------------------------
            user.setCompany(company);
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            model.addAttribute("user", user);
            return "registrationSuccessful";
        }
        return "formRegisterCompany";
    }
}
