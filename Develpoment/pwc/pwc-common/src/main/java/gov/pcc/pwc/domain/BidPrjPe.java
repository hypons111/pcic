package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjPe.
 */
@Entity
@Table(name = "bid_prj_pe")
public class BidPrjPe implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 21)
    @Column(name = "wkut", length = 21, nullable = false)
    private String wkut;

    @NotNull
    @Size(max = 40)
    @Column(name = "prjno", length = 40, nullable = false)
    private String prjno;

    @NotNull
    @Size(max = 10)
    @Id
    @Column(name = "id", length = 10, nullable = false)
    private String id;

    @NotNull
    @Size(max = 200)
    @Column(name = "pname", length = 200, nullable = false)
    private String pname;

    @Size(max = 1)
    @Column(name = "job", length = 1)
    private String job;

    @Size(max = 40)
    @Column(name = "cert_no", length = 40)
    private String certNo;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Size(max = 1)
    @Column(name = "duty", length = 1)
    private String duty;

    @NotNull
    @Size(max = 1)
    @Column(name = "job_type", length = 1, nullable = false)
    private String jobType;

    @Size(max = 30)
    @Column(name = "tel", length = 30)
    private String tel;

    @Size(max = 1)
    @Column(name = "job_kind_1", length = 1)
    private String jobKind1;

    @Size(max = 1)
    @Column(name = "job_kind_2", length = 1)
    private String jobKind2;

    @Size(max = 1)
    @Column(name = "job_kind_3", length = 1)
    private String jobKind3;

    @Size(max = 1)
    @Column(name = "job_kind_4", length = 1)
    private String jobKind4;

    @Column(name = "start_date_1")
    private LocalDate startDate1;

    @Column(name = "end_date_1")
    private LocalDate endDate1;

    @Column(name = "start_date_2")
    private LocalDate startDate2;

    @Column(name = "end_date_2")
    private LocalDate endDate2;

    @Column(name = "start_date_3")
    private LocalDate startDate3;

    @Column(name = "end_date_3")
    private LocalDate endDate3;

    @Column(name = "start_date_4")
    private LocalDate startDate4;

    @Column(name = "end_date_4")
    private LocalDate endDate4;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    @Column(name = "update_date")
    private Instant updateDate;

    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjPe wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjPe prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getId() {
        return this.id;
    }

    public BidPrjPe id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPname() {
        return this.pname;
    }

    public BidPrjPe pname(String pname) {
        this.setPname(pname);
        return this;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getJob() {
        return this.job;
    }

    public BidPrjPe job(String job) {
        this.setJob(job);
        return this;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCertNo() {
        return this.certNo;
    }

    public BidPrjPe certNo(String certNo) {
        this.setCertNo(certNo);
        return this;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public BidPrjPe startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public BidPrjPe endDate(LocalDate endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDuty() {
        return this.duty;
    }

    public BidPrjPe duty(String duty) {
        this.setDuty(duty);
        return this;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getJobType() {
        return this.jobType;
    }

    public BidPrjPe jobType(String jobType) {
        this.setJobType(jobType);
        return this;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getTel() {
        return this.tel;
    }

    public BidPrjPe tel(String tel) {
        this.setTel(tel);
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getJobKind1() {
        return this.jobKind1;
    }

    public BidPrjPe jobKind1(String jobKind1) {
        this.setJobKind1(jobKind1);
        return this;
    }

    public void setJobKind1(String jobKind1) {
        this.jobKind1 = jobKind1;
    }

    public String getJobKind2() {
        return this.jobKind2;
    }

    public BidPrjPe jobKind2(String jobKind2) {
        this.setJobKind2(jobKind2);
        return this;
    }

    public void setJobKind2(String jobKind2) {
        this.jobKind2 = jobKind2;
    }

    public String getJobKind3() {
        return this.jobKind3;
    }

    public BidPrjPe jobKind3(String jobKind3) {
        this.setJobKind3(jobKind3);
        return this;
    }

    public void setJobKind3(String jobKind3) {
        this.jobKind3 = jobKind3;
    }

    public String getJobKind4() {
        return this.jobKind4;
    }

    public BidPrjPe jobKind4(String jobKind4) {
        this.setJobKind4(jobKind4);
        return this;
    }

    public void setJobKind4(String jobKind4) {
        this.jobKind4 = jobKind4;
    }

    public LocalDate getStartDate1() {
        return this.startDate1;
    }

    public BidPrjPe startDate1(LocalDate startDate1) {
        this.setStartDate1(startDate1);
        return this;
    }

    public void setStartDate1(LocalDate startDate1) {
        this.startDate1 = startDate1;
    }

    public LocalDate getEndDate1() {
        return this.endDate1;
    }

    public BidPrjPe endDate1(LocalDate endDate1) {
        this.setEndDate1(endDate1);
        return this;
    }

    public void setEndDate1(LocalDate endDate1) {
        this.endDate1 = endDate1;
    }

    public LocalDate getStartDate2() {
        return this.startDate2;
    }

    public BidPrjPe startDate2(LocalDate startDate2) {
        this.setStartDate2(startDate2);
        return this;
    }

    public void setStartDate2(LocalDate startDate2) {
        this.startDate2 = startDate2;
    }

    public LocalDate getEndDate2() {
        return this.endDate2;
    }

    public BidPrjPe endDate2(LocalDate endDate2) {
        this.setEndDate2(endDate2);
        return this;
    }

    public void setEndDate2(LocalDate endDate2) {
        this.endDate2 = endDate2;
    }

    public LocalDate getStartDate3() {
        return this.startDate3;
    }

    public BidPrjPe startDate3(LocalDate startDate3) {
        this.setStartDate3(startDate3);
        return this;
    }

    public void setStartDate3(LocalDate startDate3) {
        this.startDate3 = startDate3;
    }

    public LocalDate getEndDate3() {
        return this.endDate3;
    }

    public BidPrjPe endDate3(LocalDate endDate3) {
        this.setEndDate3(endDate3);
        return this;
    }

    public void setEndDate3(LocalDate endDate3) {
        this.endDate3 = endDate3;
    }

    public LocalDate getStartDate4() {
        return this.startDate4;
    }

    public BidPrjPe startDate4(LocalDate startDate4) {
        this.setStartDate4(startDate4);
        return this;
    }

    public void setStartDate4(LocalDate startDate4) {
        this.startDate4 = startDate4;
    }

    public LocalDate getEndDate4() {
        return this.endDate4;
    }

    public BidPrjPe endDate4(LocalDate endDate4) {
        this.setEndDate4(endDate4);
        return this;
    }

    public void setEndDate4(LocalDate endDate4) {
        this.endDate4 = endDate4;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjPe createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjPe createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjPe updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjPe updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjPe)) {
            return false;
        }
        return id != null && id.equals(((BidPrjPe) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjPe{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
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
