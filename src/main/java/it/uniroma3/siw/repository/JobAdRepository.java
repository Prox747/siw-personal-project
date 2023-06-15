package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Company;
import it.uniroma3.siw.model.JobAd;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface JobAdRepository extends CrudRepository<JobAd, Long> {
    Set<JobAd> findTop15ByOrderByIdDesc();
}
