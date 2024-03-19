package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.GeoMember} entity.
 */
public class Geo0601DTO implements Serializable {


    private int memberCount;

    private int companyCount;

    private int jobCount;

    private int qaReview;

    private int companyReview;

    private int jobReview;

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public int getCompanyCount() {
        return companyCount;
    }

    public void setCompanyCount(int companyCount) {
        this.companyCount = companyCount;
    }

    public int getJobCount() {
        return jobCount;
    }

    public void setJobCount(int jobCount) {
        this.jobCount = jobCount;
    }

    public int getQaReview() {
        return qaReview;
    }

    public void setQaReview(int qaReview) {
        this.qaReview = qaReview;
    }

    public int getCompanyReview() {
        return companyReview;
    }

    public void setCompanyReview(int companyReview) {
        this.companyReview = companyReview;
    }

    public int getJobReview() {
        return jobReview;
    }

    public void setJobReview(int jobReview) {
        this.jobReview = jobReview;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Geo0601DTO{" +
                "memberCount=" + getMemberCount() +
                ", companyCount='" + getCompanyCount()+ "'" +
                ", jobCount='" + getJobCount() + "'" +
                ", qaReview='" + getQaReview() + "'" +
                ", companyReview='" + getCompanyReview() + "'" +
                ", jobReview='" + getJobReview() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Geo0601DTO that = (Geo0601DTO) o;

        if (memberCount != that.memberCount) return false;
        if (companyCount != that.companyCount) return false;
        if (jobCount != that.jobCount) return false;
        if (qaReview != that.qaReview) return false;
        if (companyReview != that.companyReview) return false;
        return jobReview == that.jobReview;
    }

    @Override
    public int hashCode() {
        int result = memberCount;
        result = 31 * result + companyCount;
        result = 31 * result + jobCount;
        result = 31 * result + qaReview;
        result = 31 * result + companyReview;
        result = 31 * result + jobReview;
        return result;
    }
}
