package gov.pcc.pwc.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.GeoMember} entity.
 */
public class Geo0607DTO implements Serializable {


    private String companyName;
    private int personCount;
    private int offerCount;
    private String jobKind;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public int getOfferCount() {
        return offerCount;
    }

    public void setOfferCount(int offerCount) {
        this.offerCount = offerCount;
    }

    public String getJobKind() {
        return jobKind;
    }

    public void setJobKind(String jobKind) {
        this.jobKind = jobKind;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Geo0607DTO{" +
                "companyName=" + getCompanyName() +
                ", personCount='" + getPersonCount()+ "'" +
                ", offerCount='" + getOfferCount() + "'" +
                ", jobKind='" + getJobKind() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Geo0607DTO that = (Geo0607DTO) o;

        if (companyName != that.companyName) return false;
        if (personCount != that.personCount) return false;
        if (jobKind != that.jobKind) return false;
        return offerCount == that.offerCount;
    }

}
