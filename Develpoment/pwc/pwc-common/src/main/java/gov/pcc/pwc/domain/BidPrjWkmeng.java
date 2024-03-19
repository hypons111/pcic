package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjWkmeng.
 */
@Entity
@Table(name = "bid_prj_wkmeng")
public class BidPrjWkmeng implements Serializable {

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
    @Column(name = "full_time", length = 1)
    private String fullTime;

    @NotNull
    @Size(max = 1)
    @Column(name = "job", length = 1, nullable = false)
    private String job;

    @Size(max = 80)
    @Column(name = "job_type", length = 80)
    private String jobType;

    @Size(max = 40)
    @Column(name = "work_type", length = 40)
    private String workType;

    @Size(max = 40)
    @Column(name = "swork_type", length = 40)
    private String sworkType;

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

    @Size(max = 1)
    @Column(name = "is_joing", length = 1)
    private String isJoing;

    @Size(max = 1)
    @Column(name = "re_entry", length = 1)
    private String reEntry;

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

    @Size(max = 2000)
    @Column(name = "remark", length = 2000)
    private String remark;

    @Size(max = 200)
    @Column(name = "man", length = 200)
    private String man;

    @Size(max = 30)
    @Column(name = "tel", length = 30)
    private String tel;

    @Size(max = 30)
    @Column(name = "tel_2", length = 30)
    private String tel2;

    @Column(name = "conf_out")
    private Float confOut;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjWkmeng wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjWkmeng prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getId() {
        return this.id;
    }

    public BidPrjWkmeng id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPname() {
        return this.pname;
    }

    public BidPrjWkmeng pname(String pname) {
        this.setPname(pname);
        return this;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getFullTime() {
        return this.fullTime;
    }

    public BidPrjWkmeng fullTime(String fullTime) {
        this.setFullTime(fullTime);
        return this;
    }

    public void setFullTime(String fullTime) {
        this.fullTime = fullTime;
    }

    public String getJob() {
        return this.job;
    }

    public BidPrjWkmeng job(String job) {
        this.setJob(job);
        return this;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobType() {
        return this.jobType;
    }

    public BidPrjWkmeng jobType(String jobType) {
        this.setJobType(jobType);
        return this;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getWorkType() {
        return this.workType;
    }

    public BidPrjWkmeng workType(String workType) {
        this.setWorkType(workType);
        return this;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getSworkType() {
        return this.sworkType;
    }

    public BidPrjWkmeng sworkType(String sworkType) {
        this.setSworkType(sworkType);
        return this;
    }

    public void setSworkType(String sworkType) {
        this.sworkType = sworkType;
    }

    public String getCertNo() {
        return this.certNo;
    }

    public BidPrjWkmeng certNo(String certNo) {
        this.setCertNo(certNo);
        return this;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public BidPrjWkmeng startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public BidPrjWkmeng endDate(LocalDate endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDuty() {
        return this.duty;
    }

    public BidPrjWkmeng duty(String duty) {
        this.setDuty(duty);
        return this;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getIsJoing() {
        return this.isJoing;
    }

    public BidPrjWkmeng isJoing(String isJoing) {
        this.setIsJoing(isJoing);
        return this;
    }

    public void setIsJoing(String isJoing) {
        this.isJoing = isJoing;
    }

    public String getReEntry() {
        return this.reEntry;
    }

    public BidPrjWkmeng reEntry(String reEntry) {
        this.setReEntry(reEntry);
        return this;
    }

    public void setReEntry(String reEntry) {
        this.reEntry = reEntry;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjWkmeng createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjWkmeng createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjWkmeng updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjWkmeng updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getRemark() {
        return this.remark;
    }

    public BidPrjWkmeng remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMan() {
        return this.man;
    }

    public BidPrjWkmeng man(String man) {
        this.setMan(man);
        return this;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public String getTel() {
        return this.tel;
    }

    public BidPrjWkmeng tel(String tel) {
        this.setTel(tel);
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel2() {
        return this.tel2;
    }

    public BidPrjWkmeng tel2(String tel2) {
        this.setTel2(tel2);
        return this;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public Float getConfOut() {
        return this.confOut;
    }

    public BidPrjWkmeng confOut(Float confOut) {
        this.setConfOut(confOut);
        return this;
    }

    public void setConfOut(Float confOut) {
        this.confOut = confOut;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjWkmeng uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjWkmeng)) {
            return false;
        }
        return id != null && id.equals(((BidPrjWkmeng) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjWkmeng{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", pname='" + getPname() + "'" +
            ", fullTime='" + getFullTime() + "'" +
            ", job='" + getJob() + "'" +
            ", jobType='" + getJobType() + "'" +
            ", workType='" + getWorkType() + "'" +
            ", sworkType='" + getSworkType() + "'" +
            ", certNo='" + getCertNo() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", duty='" + getDuty() + "'" +
            ", isJoing='" + getIsJoing() + "'" +
            ", reEntry='" + getReEntry() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", remark='" + getRemark() + "'" +
            ", man='" + getMan() + "'" +
            ", tel='" + getTel() + "'" +
            ", tel2='" + getTel2() + "'" +
            ", confOut=" + getConfOut() +
            ", uIp='" + getuIp() + "'" +
            "}";
    }
}
