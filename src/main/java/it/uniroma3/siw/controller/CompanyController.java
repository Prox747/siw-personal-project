package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Recruiter;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CompanyService;
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.util.ModelPreparationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @Autowired
    UserService userService;

    @GetMapping("/companies/{id}")
    public String showCompanyProfile(Model model, @PathVariable("id") Long companyId) {
        model.addAttribute("company", companyService.getCompany(companyId));
        return "company.html";
    }

    @PostMapping("/recruiter/modifyLogo")
    public String addOrModifyProfilePic(@RequestParam("image") MultipartFile multipartFile, Model model) {
        Recruiter user = (Recruiter) userService.getCurrentUser();
        try {
            companyService.addImageToCompany(user.getCompany(), multipartFile);
        } catch (IOException e) {
            model.addAttribute("erroreUpload", "Errore durante l'upload dell'immagine");
            return "redirect:/profile";
        }

        //user ha il cascade all
        userService.saveUser(user);

        return "redirect:/profile";
    }

}

