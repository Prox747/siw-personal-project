package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Recruiter;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.util.ModelPreparationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ModelPreparationUtil modelPreparationUtil;

    //lo user deve essere registrato o admin per accedere
    @GetMapping("/profile")
    public String getProfilePageApplicant(Model model) {
        User currentUser = userService.getCurrentUser();
        if(currentUser instanceof Recruiter) {
            model.addAttribute("recruiter", currentUser);
            model.addAttribute("company", ((Recruiter)currentUser).getCompany());
            return "recruiter/recruiterProfile.html";
        } else {
            model.addAttribute("user", userService.getCurrentUser());
            model.addAttribute("jobAppls", userService.getCurrentUser().getJobApplications());
            return "applicant/applicantProfile.html";
        }
    }

    @PostMapping("/applicant/addedPic")
    public String addOrModifyProfilePic(@RequestParam("image") MultipartFile multipartFile, Model model) {
        User user = userService.getCurrentUser();
        try {
            userService.addImageToUser(user, multipartFile);
        } catch (IOException e) {
            model.addAttribute("erroreUpload", "Errore durante l'upload dell'immagine");
            return "redirect:/profile";
        }
        userService.saveUser(user);

        return "redirect:/profile";
    }
}
