package it.uniroma3.siw.service;

import it.uniroma3.siw.model.JobAd;
import it.uniroma3.siw.repository.JobAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class JobAdService {
    
    @Autowired
    JobAdRepository jobAdRepository;

    @Transactional
    public JobAd getJobAd(Long id) {
        Optional<JobAd> result = this.jobAdRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional
    public JobAd saveJobAd(JobAd jobAd) {
        return this.jobAdRepository.save(jobAd);
    }

    @Transactional
    public void deleteJobAd(JobAd jobAdToDelete) {
        this.jobAdRepository.delete(jobAdToDelete);
    }

    @Transactional
    public Set<JobAd> findLast15JobAds() {
        return this.jobAdRepository.findTop15ByOrderByIdDesc();
    }
}
