package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Company;
import it.uniroma3.siw.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CompanyService {
    
    @Autowired
    CompanyRepository companyRepository;

    @Transactional
    public Company getCompany(Long id) {
        Optional<Company> result = this.companyRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional
    public Company saveCompany(Company company) {
        return this.companyRepository.save(company);
    }
}
