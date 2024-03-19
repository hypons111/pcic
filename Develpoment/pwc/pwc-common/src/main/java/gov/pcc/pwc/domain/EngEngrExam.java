package gov.pcc.pwc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A EngEngrExam.
 */
@Entity
@Table(name = "eng_engr_exam")
public class EngEngrExam implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "eng_engr_exam_id", length = 20, nullable = false)
    private String engEngrExamId;

    @Size(max = 20)
    @Column(name = "exam_no1", length = 20)
    private String examNo1;

    @Size(max = 20)
    @Column(name = "exam_no2", length = 20)
    private String examNo2;

    @Size(max = 5)
    @Column(name = "subject", length = 5)
    private String subject;

    @Column(name = "ranking", precision = 21, scale = 2)
    private BigDecimal ranking;

    @Size(max = 60)
    @Column(name = "name", length = 60)
    private String name;

    @Size(max = 20)
    @Column(name = "idno", length = 20)
    private String idno;

    @Size(max = 1)
    @Column(name = "sex", length = 1)
    private String sex;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Size(max = 100)
    @Column(name = "education", length = 100)
    private String education;

    @Size(max = 10)
    @Column(name = "addr_zip", length = 10)
    private String addrZip;

    @Size(max = 5)
    @Column(name = "addr_city", length = 5)
    private String addrCity;

    @Size(max = 3)
    @Column(name = "addr_town", length = 3)
    private String addrTown;

    @Size(max = 100)
    @Column(name = "addr_other", length = 100)
    private String addrOther;

    @Column(name = "import_date")
    private LocalDate importDate;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    @NotNull
    @Size(max = 20)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    @Size(max = 100)
    @Column(name = "transfer_from", length = 100)
    private String transferFrom;

    @Column(name = "transfer_time")
    private Instant transferTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getEngEngrExamId() {
        return this.engEngrExamId;
    }

    public EngEngrExam engEngrExamId(String engEngrExamId) {
        this.setEngEngrExamId(engEngrExamId);
        return this;
    }

    public void setEngEngrExamId(String engEngrExamId) {
        this.engEngrExamId = engEngrExamId;
    }

    public String getExamNo1() {
        return this.examNo1;
    }

    public EngEngrExam examNo1(String examNo1) {
        this.setExamNo1(examNo1);
        return this;
    }

    public void setExamNo1(String examNo1) {
        this.examNo1 = examNo1;
    }

    public String getExamNo2() {
        return this.examNo2;
    }

    public EngEngrExam examNo2(String examNo2) {
        this.setExamNo2(examNo2);
        return this;
    }

    public void setExamNo2(String examNo2) {
        this.examNo2 = examNo2;
    }

    public String getSubject() {
        return this.subject;
    }

    public EngEngrExam subject(String subject) {
        this.setSubject(subject);
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public BigDecimal getRanking() {
        return this.ranking;
    }

    public EngEngrExam ranking(BigDecimal ranking) {
        this.setRanking(ranking);
        return this;
    }

    public void setRanking(BigDecimal ranking) {
        this.ranking = ranking;
    }

    public String getName() {
        return this.name;
    }

    public EngEngrExam name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdno() {
        return this.idno;
    }

    public EngEngrExam idno(String idno) {
        this.setIdno(idno);
        return this;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getSex() {
        return this.sex;
    }

    public EngEngrExam sex(String sex) {
        this.setSex(sex);
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public EngEngrExam birthday(LocalDate birthday) {
        this.setBirthday(birthday);
        return this;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return this.education;
    }

    public EngEngrExam education(String education) {
        this.setEducation(education);
        return this;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAddrZip() {
        return this.addrZip;
    }

    public EngEngrExam addrZip(String addrZip) {
        this.setAddrZip(addrZip);
        return this;
    }

    public void setAddrZip(String addrZip) {
        this.addrZip = addrZip;
    }

    public String getAddrCity() {
        return this.addrCity;
    }

    public EngEngrExam addrCity(String addrCity) {
        this.setAddrCity(addrCity);
        return this;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrTown() {
        return this.addrTown;
    }

    public EngEngrExam addrTown(String addrTown) {
        this.setAddrTown(addrTown);
        return this;
    }

    public void setAddrTown(String addrTown) {
        this.addrTown = addrTown;
    }

    public String getAddrOther() {
        return this.addrOther;
    }

    public EngEngrExam addrOther(String addrOther) {
        this.setAddrOther(addrOther);
        return this;
    }

    public void setAddrOther(String addrOther) {
        this.addrOther = addrOther;
    }

    public LocalDate getImportDate() {
        return this.importDate;
    }

    public EngEngrExam importDate(LocalDate importDate) {
        this.setImportDate(importDate);
        return this;
    }

    public void setImportDate(LocalDate importDate) {
        this.importDate = importDate;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EngEngrExam updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EngEngrExam updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EngEngrExam createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EngEngrExam createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public EngEngrExam transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return this.transferTime;
    }

    public EngEngrExam transferTime(Instant transferTime) {
        this.setTransferTime(transferTime);
        return this;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngEngrExam)) {
            return false;
        }
        return engEngrExamId != null && engEngrExamId.equals(((EngEngrExam) o).engEngrExamId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngEngrExam{" +
            "engEngrExamId=" + getEngEngrExamId() +
            ", examNo1='" + getExamNo1() + "'" +
            ", examNo2='" + getExamNo2() + "'" +
            ", subject='" + getSubject() + "'" +
            ", ranking=" + getRanking() +
            ", name='" + getName() + "'" +
            ", idno='" + getIdno() + "'" +
            ", sex='" + getSex() + "'" +
            ", birthday='" + getBirthday() + "'" +
            ", education='" + getEducation() + "'" +
            ", addrZip='" + getAddrZip() + "'" +
            ", addrCity='" + getAddrCity() + "'" +
            ", addrTown='" + getAddrTown() + "'" +
            ", addrOther='" + getAddrOther() + "'" +
            ", importDate='" + getImportDate() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", transferFrom='" + getTransferFrom() + "'" +
            ", transferTime='" + getTransferTime() + "'" +
            "}";
    }
}
