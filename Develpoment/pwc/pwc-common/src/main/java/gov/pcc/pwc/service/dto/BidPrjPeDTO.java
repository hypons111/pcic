package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjPe} entity.
 */
public class BidPrjPeDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    @Size(max = 10)
    private String id;

    @NotNull
    @Size(max = 200)
    private String pname;

    @Size(max = 1)
    private String job;

    @Size(max = 40)
    private String certNo;

    private LocalDate startDate;

    private LocalDate endDate;

    @Size(max = 1)
    private String duty;

    @NotNull
    @Size(max = 1)
    private String jobType;

    @Size(max = 30)
    private String tel;

    @Size(max = 1)
    private String jobKind1;

    @Size(max = 1)
    private String jobKind2;

    @Size(max = 1)
    private String jobKind3;

    @Size(max = 1)
    private String jobKind4;

    private LocalDate startDate1;

    private LocalDate endDate1;

    private LocalDate startDate2;

    private LocalDate endDate2;

    private LocalDate startDate3;

    private LocalDate endDate3;

    private LocalDate startDate4;

    private LocalDate endDate4;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    public String getWkut() {
        return wkut;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return prjno;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getJobKind1() {
        return jobKind1;
    }

    public void setJobKind1(String jobKind1) {
        this.jobKind1 = jobKind1;
    }

    public String getJobKind2() {
        return jobKind2;
    }

    public void setJobKind2(String jobKind2) {
        this.jobKind2 = jobKind2;
    }

    public String getJobKind3() {
        return jobKind3;
    }

    public void setJobKind3(String jobKind3) {
        this.jobKind3 = jobKind3;
    }

    public String getJobKind4() {
        return jobKind4;
    }

    public void setJobKind4(String jobKind4) {
        this.jobKind4 = jobKind4;
    }

    public LocalDate getStartDate1() {
        return startDate1;
    }

    public void setStartDate1(LocalDate startDate1) {
        this.startDate1 = startDate1;
    }

    public LocalDate getEndDate1() {
        return endDate1;
    }

    public void setEndDate1(LocalDate endDate1) {
        this.endDate1 = endDate1;
    }

    public LocalDate getStartDate2() {
        return startDate2;
    }

    public void setStartDate2(LocalDate startDate2) {
        this.startDate2 = startDate2;
    }

    public LocalDate getEndDate2() {
        return endDate2;
    }

    public void setEndDate2(LocalDate endDate2) {
        this.endDate2 = endDate2;
    }

    public LocalDate getStartDate3() {
        return startDate3;
    }

    public void setStartDate3(LocalDate startDate3) {
        this.startDate3 = startDate3;
    }

    public LocalDate getEndDate3() {
        return endDate3;
    }

    public void setEndDate3(LocalDate endDate3) {
        this.endDate3 = endDate3;
    }

    public LocalDate getStartDate4() {
        return startDate4;
    }

    public void setStartDate4(LocalDate startDate4) {
        this.startDate4 = startDate4;
    }

    public LocalDate getEndDate4() {
        return endDate4;
    }

    public void setEndDate4(LocalDate endDate4) {
        this.endDate4 = endDate4;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjPeDTO)) {
            return false;
        }

        BidPrjPeDTO bidPrjPeDTO = (BidPrjPeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, bidPrjPeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjPeDTO{" +
            "wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", id='" + getId() + "'" +
            ", pname='" + getPname() + "'" +
            ", job='" + getJob() + "'" +
            ", certNo='" + getCertNo() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", duty='" + getDuty() + "'" +
            ", jobType='" + getJobType() + "'" +
            ", tel='" + getTel() + "'" +
            ", jobKind1='" + getJobKind1() + "'" +
            ", jobKind2='" + getJobKind2() + "'" +
            ", jobKind3='" + getJobKind3() + "'" +
            ", jobKind4='" + getJobKind4() + "'" +
            ", startDate1='" + getStartDate1() + "'" +
            ", endDate1='" + getEndDate1() + "'" +
            ", startDate2='" + getStartDate2() + "'" +
            ", endDate2='" + getEndDate2() + "'" +
            ", startDate3='" + getStartDate3() + "'" +
            ", endDate3='" + getEndDate3() + "'" +
            ", startDate4='" + getStartDate4() + "'" +
            ", endDate4='" + getEndDate4() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
