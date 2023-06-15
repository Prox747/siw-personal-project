package it.uniroma3.siw.service;

import it.uniroma3.siw.model.JobAd;
import it.uniroma3.siw.model.JobApplication;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class JobApplicationService {
    
    @Autowired
    JobApplicationRepository jobApplicationRepository;

    @Transactional
    public JobApplication getJobApplication(Long id) {
        Optional<JobApplication> result = this.jobApplicationRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional
    public JobApplication saveJobApplication(JobApplication jobApplication) {
        return this.jobApplicationRepository.save(jobApplication);
    }

    @Transactional
    public void acceptApplication(Long applicationId) {
        JobApplication jobApplication = this.getJobApplication(applicationId);
        jobApplication.setStatus(JobApplication.STATUS_ACCEPTED);
        this.saveJobApplication(jobApplication);
    }

    @Transactional
    public void rejectApplication(Long applicationId) {
        JobApplication jobApplication = this.getJobApplication(applicationId);
        jobApplication.setStatus(JobApplication.STATUS_REJECTED);
        this.saveJobApplication(jobApplication);
    }

    @Transactional
    public void applicateToJobAd(JobAd jobAd, User applicant) {
        JobApplication jobApplication = new JobApplication();
        jobApplication.setJobAd(jobAd);
        jobApplication.setApplicant(applicant);
        jobApplication.setStatus(JobApplication.STATUS_PENDING);
        applicant.getJobApplications().add(jobApplication);
        jobAd.getJobApplications().add(jobApplication);
        this.saveJobApplication(jobApplication);
    }

    @Transactional
    public Set<JobApplication> getApplicationsForJobAd(JobAd jobAd) {
        return this.jobApplicationRepository.findAllByJobAdIs(jobAd);
    }
}
