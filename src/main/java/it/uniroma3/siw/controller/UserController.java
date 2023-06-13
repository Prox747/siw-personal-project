package it.uniroma3.siw.controller;

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
    @GetMapping("/registered/profile")
    public String getProfilePage(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "registered/profile.html";
    }

    @PostMapping("/registered/addedPic")
    public String addOrModifyProfilePic(@RequestParam("image") MultipartFile multipartFile, Model model) {
        User user = userService.getCurrentUser();
        try {
            userService.addImageToUser(user, multipartFile);
        } catch (IOException e) {
            model.addAttribute("erroreUpload", "Errore durante l'upload dell'immagine");
            return getProfilePage(model);
        }
        userService.saveUser(user);

        return getProfilePage(model);
    }
}
