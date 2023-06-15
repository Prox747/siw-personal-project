package it.uniroma3.siw.controller;

import it.uniroma3.siw.service.CompanyService;
import it.uniroma3.siw.util.ModelPreparationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping("/companies/{id}")
    public String showCompanyProfile(Model model, @PathVariable("id") Long companyId) {
        model.addAttribute("company", companyService.getCompany(companyId));
        return "company.html";
    }

}

