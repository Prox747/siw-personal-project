package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Company;
import it.uniroma3.siw.model.JobAd;
import org.springframework.data.repository.CrudRepository;

public interface JobAdRepository extends CrudRepository<JobAd, Long> {

}
