package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Company;
import it.uniroma3.siw.repository.CompanyRepository;
import it.uniroma3.siw.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Transactional
    public void addImageToCompany(Company company, MultipartFile multipartFile) throws IOException {
        //questa linea è necessaria per evitare attacchi di iniezione di codice attraverso il nome del file
        // (possono inserire un nome di file che contiene un path e quindi accedere a file che non dovrebbero o cose simili supercattive)
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        company.setLogoFileName(fileName);
        String uploadDir = "src/main/upload/images/companiesLogos/";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    }
}
