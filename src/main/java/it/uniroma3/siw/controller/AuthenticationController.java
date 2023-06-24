package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Company;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Recruiter;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CompanyService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.JobAdService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class AuthenticationController {

    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    UserService userService;
    @Autowired
    JobAdService jobAdService;
    @Autowired
    CompanyService companyService;
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
        model.addAttribute("recruiter", new Recruiter());
        model.addAttribute("credentials", new Credentials());
        model.addAttribute("company", new Company());
        return "formRegisterCompany";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "formLogin.html";
    }

    @GetMapping("/")
    public String index(Model model) {
        return modelPreparationUtil.prepareModelForIndexTemplate(
                "index.html",
                model,
                jobAdService.findLast15JobAds(),
                jobAdService.find5MostPopularJobAds());
    }

    @GetMapping("/success")
    public String defaultAfterLogin(Model model) {
        return index(model);
    }

    @PostMapping("/registerUser")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult userBindingResult,
                               @Valid @ModelAttribute("credentials") Credentials credentials,
                               BindingResult credentialsBindingResult,
                               Model model) {

        // se user e credential hanno entrambi contenuti validi, memorizza User e le Credentials nel DB
        if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()
            && userService.isUsernameAvailable(credentials.getUsername())) {
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials, Credentials.DEFAULT_ROLE);

            //ritorniamo al login con il messaggio di successo
            model.addAttribute("registrationSuccessful", "Registrazione avvenuta con successo");
            return "formLogin";
        }
        if( !userService.isUsernameAvailable(credentials.getUsername()) )
            model.addAttribute("erroreUsername", "Username già in uso");
        return "formRegisterUser";
    }

    //-----------------------------------------------------------------  |
    //CREDENTIALS SALVA USER IN CASCADE E USER SALVA COMPANY IN CASCADE  |
    //-----------------------------------------------------------------  V
    @PostMapping("/registerCompany")
    public String registerCompany(@RequestParam("logo") MultipartFile multipartFile,
                               @Valid @ModelAttribute("company") Company company,
                               BindingResult companyBindingResult,
                               @Valid @ModelAttribute("recruiter") Recruiter recruiter,
                               BindingResult recruiterBindingResult, @Valid
                               @ModelAttribute("credentials") Credentials credentials,
                               BindingResult credentialsBindingResult,
                               Model model) {

        // se user e credential hanno entrambi contenuti validi, memorizza User e le Credentials nel DB
        if( !recruiterBindingResult.hasErrors()
                && !credentialsBindingResult.hasErrors()
                && !companyBindingResult.hasErrors()
                && !multipartFile.isEmpty()
                && userService.isUsernameAvailable(credentials.getUsername())) {
            try{
                companyService.addImageToCompany(company, multipartFile);
            } catch (IOException e) {
                model.addAttribute("erroreUpload", "Errore nel caricamento dell'immagine");
                return "formRegisterCompany";
            }
            //-----------------------------------------------------------------
            //CREDENTIALS SALVA USER IN CASCADE E USER SALVA COMPANY IN CASCADE
            //-----------------------------------------------------------------
            recruiter.setCompany(company);
            credentials.setUser(recruiter);
            credentialsService.saveCredentials(credentials, Credentials.RECRUITER_ROLE);

            //ritorniamo al login con il messaggio di successo
            model.addAttribute("registrationSuccessful", "Registrazione avvenuta con successo");
            return "formLogin";
        }
        //per validare lo user dovrei creare un oggetto wrapper per user e credentials
        //per poi poter rilevare gli errori nei global errors della form
        //perchè altrimenti mi da errore perchè vuole un th:object nella form
        if( !userService.isUsernameAvailable(credentials.getUsername()) )
            model.addAttribute("erroreUsername", "Username già in uso");
        return "formRegisterCompany";
    }
}
