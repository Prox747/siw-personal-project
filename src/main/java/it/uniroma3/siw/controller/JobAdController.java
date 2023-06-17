package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.service.CompanyService;
import it.uniroma3.siw.service.JobAdService;
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.util.ModelPreparationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
public class JobAdController {
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;
    @Autowired
    JobAdService jobAdService;
    @Autowired
    ModelPreparationUtil modelPreparationUtil;

    @GetMapping("/recruiter/newJobAd")
    public String showNewJobAdForm(Model model) {
        model.addAttribute("jobAd", new JobAd());
        return "recruiter/formAddJobAd";
    }

    @PostMapping("/recruiter/addedJobAd")
    public String addJobAd(@Valid @ModelAttribute("jobAd") JobAd jobAd, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            //in toeria non può essere null, per invocare questo metodo bisogna
            // essere loggati come reclutatori, quindi avere un'azienda
            Company company = ((Recruiter)userService.getCurrentUser()).getCompany();
            jobAd.setCompany(company);
            jobAd.setPublicationDate(LocalDateTime.now());
            company.getJobAds().add(jobAd);
            //company ha il cascade all
            companyService.saveCompany(company);
            return modelPreparationUtil.prepareModelForIndexTemplate("index.html", model,
                    jobAdService.findLast15JobAds(),
                    jobAdService.find5MostPopularJobAds());
        } else {
            return "recruiter/formAddJobAd";
        }
    }

    @GetMapping("/recruiter/deleteJobAd/{id}")
    public String deleteJobAd(@PathVariable("id") Long jobAdId, Model model) {
        Company company = ((Recruiter)userService.getCurrentUser()).getCompany();
        JobAd jobAdToDelete = jobAdService.getJobAd(jobAdId);
        company.getJobAds().remove(jobAdToDelete);

        //rimuovo le jobApplication associate agli utenti
        for (JobApplication jobApplication : jobAdToDelete.getJobApplications()) {
            User user = jobApplication.getApplicant();
            user.getJobApplications().remove(jobApplication);
            userService.saveUser(user);
        }

        jobAdService.deleteJobAd(jobAdToDelete);
        companyService.saveCompany(company);
        return modelPreparationUtil.prepareModelForIndexTemplate("index.html", model,
                jobAdService.findLast15JobAds(),
                jobAdService.find5MostPopularJobAds());
    }
}
