package it.uniroma3.siw.service;

import it.uniroma3.siw.model.JobAd;
import it.uniroma3.siw.repository.JobAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    //ho provato a farlo col db ma era difficile, la mia prova è nella repository
    @Transactional
    public List<JobAd> find5MostPopularJobAds() {
        List<JobAd> allJobAds = (List<JobAd>) jobAdRepository.findAll();
        List<JobAd> top5JobAds = new ArrayList<>();

        allJobAds.sort((a, b) -> b.getJobApplications().size() - a.getJobApplications().size());

        //per ogni jobAd trovata, al massimo 5, la aggiungo alla lista da ritornare
        for(int i = 0; i < Math.min(5, allJobAds.size()); i++) {
            if(allJobAds.get(i).getJobApplications().size() > 0)
                top5JobAds.add(allJobAds.get(i));
        }

        return top5JobAds;
    }

    public void updateJobAd(JobAd oldJobAd, JobAd editedJobAd) {
        oldJobAd.setDescription(editedJobAd.getDescription());
        oldJobAd.setRequirements(editedJobAd.getRequirements());
        oldJobAd.setSalary(editedJobAd.getSalary());
        oldJobAd.setTitle(editedJobAd.getTitle());
        oldJobAd.setField(editedJobAd.getField());
    }
}
