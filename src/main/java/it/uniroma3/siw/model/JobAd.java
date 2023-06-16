package it.uniroma3.siw.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="job_ads")
public class JobAd {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="id_company")
    private Company company;
    @OneToMany(mappedBy = "jobAd", cascade = CascadeType.ALL)
    private Set<JobApplication> jobApplications;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String field;
    @NotBlank
    private String requirements;
    @NotNull
    @Max(100000)
    @Min(200)
    private int salary;
    private LocalDateTime publicationDate;

    public JobAd() { jobApplications = new HashSet<>(); }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Set<JobApplication> getJobApplications() {
        return jobApplications;
    }

    public void setJobApplications(Set<JobApplication> jobApplications) {
        this.jobApplications = jobApplications;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobAd jobAd = (JobAd) o;
        return Objects.equals(id, jobAd.id) && Objects.equals(company, jobAd.company) && Objects.equals(title, jobAd.title) && Objects.equals(description, jobAd.description) && Objects.equals(field, jobAd.field) && Objects.equals(requirements, jobAd.requirements) && Objects.equals(publicationDate, jobAd.publicationDate);
    }
}
