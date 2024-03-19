package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EngEngrExam} entity.
 */
public class EngEngrExamDTO implements Serializable {

    @NotNull
    @Size(max = 20)
    private String engEngrExamId;

    @Size(max = 20)
    private String examNo1;

    @Size(max = 20)
    private String examNo2;

    @Size(max = 5)
    private String subject;

    private BigDecimal ranking;

    @Size(max = 60)
    private String name;

    @Size(max = 20)
    private String idno;

    @Size(max = 1)
    private String sex;

    private LocalDate birthday;

    @Size(max = 100)
    private String education;

    @Size(max = 10)
    private String addrZip;

    @Size(max = 5)
    private String addrCity;

    @Size(max = 3)
    private String addrTown;

    @Size(max = 100)
    private String addrOther;

    private LocalDate importDate;

    @NotNull
    private Instant updateTime;

    @NotNull
    @Size(max = 20)
    private String updateUser;

    @NotNull
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    private String createUser;

    @Size(max = 100)
    private String transferFrom;

    private Instant transferTime;

    public String getEngEngrExamId() {
        return engEngrExamId;
    }

    public void setEngEngrExamId(String engEngrExamId) {
        this.engEngrExamId = engEngrExamId;
    }

    public String getExamNo1() {
        return examNo1;
    }

    public void setExamNo1(String examNo1) {
        this.examNo1 = examNo1;
    }

    public String getExamNo2() {
        return examNo2;
    }

    public void setExamNo2(String examNo2) {
        this.examNo2 = examNo2;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public BigDecimal getRanking() {
        return ranking;
    }

    public void setRanking(BigDecimal ranking) {
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAddrZip() {
        return addrZip;
    }

    public void setAddrZip(String addrZip) {
        this.addrZip = addrZip;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrTown() {
        return addrTown;
    }

    public void setAddrTown(String addrTown) {
        this.addrTown = addrTown;
    }

    public String getAddrOther() {
        return addrOther;
    }

    public void setAddrOther(String addrOther) {
        this.addrOther = addrOther;
    }

    public LocalDate getImportDate() {
        return importDate;
    }

    public void setImportDate(LocalDate importDate) {
        this.importDate = importDate;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngEngrExamDTO)) {
            return false;
        }

        EngEngrExamDTO engEngrExamDTO = (EngEngrExamDTO) o;
        if (this.engEngrExamId == null) {
            return false;
        }
        return Objects.equals(this.engEngrExamId, engEngrExamDTO.engEngrExamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.engEngrExamId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngEngrExamDTO{" +
            "engEngrExamId='" + getEngEngrExamId() + "'" +
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
