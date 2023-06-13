package it.uniroma3.siw.model;

import javax.persistence.*;
import java.time.LocalDate;
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
    private String title;
    private String description;
    private String field;
    private String requisites;
    private LocalDate publicationDate;

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

    public String getRequisites() {
        return requisites;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobAd jobAd = (JobAd) o;
        return Objects.equals(id, jobAd.id) && Objects.equals(company, jobAd.company) && Objects.equals(title, jobAd.title) && Objects.equals(description, jobAd.description) && Objects.equals(field, jobAd.field) && Objects.equals(requisites, jobAd.requisites) && Objects.equals(publicationDate, jobAd.publicationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, title, description, field, requisites, publicationDate);
    }
}
