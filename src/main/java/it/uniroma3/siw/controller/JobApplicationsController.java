package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.JobAd;
import it.uniroma3.siw.service.JobAdService;
import it.uniroma3.siw.service.JobApplicationService;
import it.uniroma3.siw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class JobApplicationsController {
    @Autowired
    JobApplicationService jobApplicationService;
    @Autowired
    JobAdService jobAdService;
    @Autowired
    UserService userService;

    @GetMapping("/recruiter/jobAds/{jobAdId}")
    public String showJobAdApplications(@PathVariable("jobAdId") Long jobAdId, Model model) {
        JobAd jobAd = jobAdService.getJobAd(jobAdId);
        model.addAttribute("jobAd", jobAd);
        model.addAttribute("jobAppls", jobApplicationService.getApplicationsForJobAd(jobAd));
        return "/recruiter/jobAdApplications";
    }

    @GetMapping("/recruiter/jobAds/{jobAdId}/applications/{applicationId}/accept")
    public String acceptApplication(@PathVariable("jobAdId") Long jobAdId,
                                    @PathVariable("applicationId") Long applicationId,
                                    Model model) {
        jobApplicationService.acceptApplication(applicationId);
        return "redirect:/recruiter/jobAds/{jobAdId}";
    }

    @GetMapping("/recruiter/jobAds/{jobAdId}/applications/{applicationId}/reject")
    public String rejectApplication(@PathVariable("jobAdId") Long jobAdId,
                                    @PathVariable("applicationId") Long applicationId,
                                    Model model) {
        jobApplicationService.rejectApplication(applicationId);
        return "redirect:/recruiter/jobAds/{jobAdId}";
    }

    @GetMapping("/applicate/{jobAdId}")
    public String applicate(@PathVariable("jobAdId") Long jobAdId, Model model) {
        jobApplicationService.applicateToJobAd(jobAdService.getJobAd(jobAdId), userService.getCurrentUser());
        return "redirect:/";
    }
}
