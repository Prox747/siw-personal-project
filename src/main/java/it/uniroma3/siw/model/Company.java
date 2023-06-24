package it.uniroma3.siw.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    private String companyName;
    @Column(length=1500)
    @NotBlank
    private String description;
    @NotBlank
    private String location;
    private String logoFileName;

    @Pattern(regexp="^[0-9]{10}$", message="Formato non valido")
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

    public String getLogoFileName() {
        return logoFileName;
    }

    public void setLogoFileName(String logoFileName) {
        this.logoFileName = logoFileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) && Objects.equals(companyName, company.companyName) && Objects.equals(description, company.description) && Objects.equals(location, company.location) && Objects.equals(phoneNumber, company.phoneNumber) && Objects.equals(jobAds, company.jobAds);
    }
}
