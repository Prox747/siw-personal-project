package it.uniroma3.siw.model;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("recruiter")
public class Recruiter extends User{

    @OneToOne(cascade = CascadeType.ALL)
    private Company company;

    private int acceptedApplications = 0;
    private int rejectedApplications = 0;

    public Recruiter() {
        super();
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getAcceptedApplications() {
        return acceptedApplications;
    }

    public void setAcceptedApplications(int acceptedApplications) {
        this.acceptedApplications = acceptedApplications;
    }

    public int getRejectedApplications() {
        return rejectedApplications;
    }

    public void setRejectedApplications(int rejectedApplications) {
        this.rejectedApplications = rejectedApplications;
    }
}
