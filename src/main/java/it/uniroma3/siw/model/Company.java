package it.uniroma3.siw.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="companies")
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    // I cannot save it "name" because thymeleaf will give it the same name attribute as the one in User.java
    private String companyName;
    private String description;
    private String location;
    @Pattern(regexp="^[0-9]{10}$", message="Numero di telefono non valido")
    private String phoneNumber;
    @OneToMany(mappedBy="company", cascade = CascadeType.ALL)
    private Set<JobAd> jobAds;

    public Company() { jobAds = new HashSet<>();}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<JobAd> getJobAds() {
        return jobAds;
    }

    public void setJobAds(Set<JobAd> jobAds) {
        this.jobAds = jobAds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) && Objects.equals(companyName, company.companyName) && Objects.equals(description, company.description) && Objects.equals(location, company.location) && Objects.equals(phoneNumber, company.phoneNumber) && Objects.equals(jobAds, company.jobAds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, description, location, phoneNumber, jobAds);
    }
}
