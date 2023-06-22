package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Company;
import it.uniroma3.siw.model.JobAd;
import it.uniroma3.siw.model.JobApplication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface JobApplicationRepository extends CrudRepository<JobApplication, Long> {

    Set<JobApplication> findAllByJobAdIs(JobAd jobAd);

    List<JobApplication> findAllByJobAd_CompanyAndStatusIs(Company company, String statusPending);
}
