package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjQc.
 */
@Entity
@IdClass(BidPrjQcPrimaryKey.class)
@Table(name = "bid_prj_qc")
public class BidPrjQc implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @Size(max = 21)
    @Column(name = "wkut", length = 21)
    private String wkut;

    @Id
    @Size(max = 40)
    @Column(name = "prjno", length = 40)
    private String prjno;

    @Size(max = 10)
    @Id
    @Column(name = "id", length = 10)
    private String id;

    @Size(max = 2)
    @Column(name = "dsts", length = 2)
    private String dsts;

    @Size(max = 1)
    @Column(name = "job_type", length = 1)
    private String jobType;

    @Size(max = 20)
    @Column(name = "major", length = 20)
    private String major;

    @Size(max = 100)
    @Column(name = "company", length = 100)
    private String company;

    @Size(max = 30)
    @Column(name = "site_tel", length = 30)
    private String siteTel;

    @Id
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Size(max = 1)
    @Column(name = "duty", length = 1)
    private String duty;

    @Size(max = 1)
    @Column(name = "re_entry", length = 1)
    private String reEntry;

    @Column(name = "create_date")
    private Instant createDate;

    @Size(max = 20)
    @Column(name = "create_user", length = 20)
    private String createUser;

    @Column(name = "update_date")
    private Instant updateDate;

    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    @Size(max = 2000)
    @Column(name = "remark", length = 2000)
    private String remark;

    @Size(max = 20)
    @Column(name = "man", length = 20)
    private String man;

    @Size(max = 30)
    @Column(name = "tel", length = 30)
    private String tel;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    // 複合主鍵
    public BidPrjQcPrimaryKey getPrjQcKey() {
        return new BidPrjQcPrimaryKey(wkut, prjno, id, startDate);
    }

    public void setPrjQcKey(BidPrjQcPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.id = id.getId();
        this.startDate = id.getStartDate();
    }


    public String getWkut() {
        return this.wkut;
    }

    public BidPrjQc wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjQc prjno(String prjNo) {
        this.setPrjno(prjNo);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getId() {
        return this.id;
    }

    public BidPrjQc id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDsts() {
        return this.dsts;
    }

    public BidPrjQc dsts(String dsts) {
        this.setDsts(dsts);
        return this;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public String getJobType() {
        return this.jobType;
    }

    public BidPrjQc jobType(String jobType) {
        this.setJobType(jobType);
        return this;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getMajor() {
        return this.major;
    }

    public BidPrjQc major(String major) {
        this.setMajor(major);
        return this;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCompany() {
        return this.company;
    }

    public BidPrjQc company(String company) {
        this.setCompany(company);
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSiteTel() {
        return this.siteTel;
    }

    public BidPrjQc siteTel(String siteTel) {
        this.setSiteTel(siteTel);
        return this;
    }

    public void setSiteTel(String siteTel) {
        this.siteTel = siteTel;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public BidPrjQc startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public BidPrjQc endDate(LocalDate endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDuty() {
        return this.duty;
    }

    public BidPrjQc duty(String duty) {
        this.setDuty(duty);
        return this;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getReEntry() {
        return this.reEntry;
    }

    public BidPrjQc reEntry(String reEntry) {
        this.setReEntry(reEntry);
        return this;
    }

    public void setReEntry(String reEntry) {
        this.reEntry = reEntry;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjQc createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjQc createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjQc updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjQc updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getRemark() {
        return this.remark;
    }

    public BidPrjQc remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMan() {
        return this.man;
    }

    public BidPrjQc man(String man) {
        this.setMan(man);
        return this;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public String getTel() {
        return this.tel;
    }

    public BidPrjQc tel(String tel) {
        this.setTel(tel);
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjQc)) {
            return false;
        }
        BidPrjQc that = (BidPrjQc) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && id.equals(that.id) && startDate.equals(that.startDate);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return Objects.hash(wkut, prjno, id, startDate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjQc{" +
                "bidPrjQcKey='" + getPrjQcKey() + "'" +
                "id=" + getId() +
                ", wkut='" + getWkut() + "'" +
                ", prjno='" + getPrjno() + "'" +
                ", dsts='" + getDsts() + "'" +
                ", jobType='" + getJobType() + "'" +
                ", major='" + getMajor() + "'" +
                ", company='" + getCompany() + "'" +
                ", siteTel='" + getSiteTel() + "'" +
                ", startDate='" + getStartDate() + "'" +
                ", endDate='" + getEndDate() + "'" +
                ", duty='" + getDuty() + "'" +
                ", reEntry='" + getReEntry() + "'" +
                ", createDate='" + getCreateDate() + "'" +
                ", createUser='" + getCreateUser() + "'" +
                ", updateDate='" + getUpdateDate() + "'" +
                ", updateUser='" + getUpdateUser() + "'" +
                ", remark='" + getRemark() + "'" +
                ", man='" + getMan() + "'" +
                ", tel='" + getTel() + "'" +
                "}";
    }
}
