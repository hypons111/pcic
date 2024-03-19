package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.GeoMember} entity.
 */
public class GeoMemberDTO implements Serializable {

    //    @NotNull
    @Size(max = 32)
    private String geoMemberId;

    @Size(max = 510)
    private String userName;

    @Size(max = 510)
    private String workTel;

    @Size(max = 10)
    private String addrZip;
    @Size(max = 10)
    private String addrCity;
    @Size(max = 10)
    private String addrTown;
    @Size(max = 510)
    private String addrOther;


    @Size(max = 510)
    private String idno;

    @Size(max = 510)
    private String userId;

    private Instant applyDate;

    @Size(max = 510)
    private String jobDemand;

    @Size(max = 1)
    private String sex;

    private LocalDate birthday;

    @Size(max = 510)
    private String email;

    @Size(max = 510)
    private String homeTel;

    @Size(max = 510)
    private String fax;

    @Size(max = 510)
    private String mobileTel;

    @Size(max = 510)
    private String examineeDegree;

    @Size(max = 510)
    private String highestDegree;

    private LocalDate firstexamDate;

    @Size(max = 510)
    private String firstexamCertNo;

    @Size(max = 510)
    private String firstexamSubject;

    private String isApprove;

    @Size(max = 100)
    private String jobType;

    @Size(max = 100)
    private String salaryRange;

    @Size(max = 510)
    private String workPlace;

    @Size(max = 510)
    private String remark;

    private String isEnable;

    @Size(max = 100)
    private String ranking;

    @Size(max = 100)
    private String serialNo;

    @Size(max = 2)
    private String jobKind;

    private Instant updateTime;

    @Size(max = 40)
    private String updateUser;

    private Instant createTime;

    @Size(max = 40)
    private String createUser;


    public String getGeoMemberId() {
        return geoMemberId;
    }

    public void setGeoMemberId(String geoMemberId) {
        this.geoMemberId = geoMemberId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkTel() {
        return workTel;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
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

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Instant applyDate) {
        this.applyDate = applyDate;
    }

    public String getJobDemand() {
        return jobDemand;
    }

    public void setJobDemand(String jobDemand) {
        this.jobDemand = jobDemand;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeTel() {
        return homeTel;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getExamineeDegree() {
        return examineeDegree;
    }

    public void setExamineeDegree(String examineeDegree) {
        this.examineeDegree = examineeDegree;
    }

    public String getHighestDegree() {
        return highestDegree;
    }

    public void setHighestDegree(String highestDegree) {
        this.highestDegree = highestDegree;
    }

    public LocalDate getFirstexamDate() {
        return firstexamDate;
    }

    public void setFirstexamDate(LocalDate firstexamDate) {
        this.firstexamDate = firstexamDate;
    }

    public String getFirstexamCertNo() {
        return firstexamCertNo;
    }

    public void setFirstexamCertNo(String firstexamCertNo) {
        this.firstexamCertNo = firstexamCertNo;
    }

    public String getFirstexamSubject() {
        return firstexamSubject;
    }

    public void setFirstexamSubject(String firstexamSubject) {
        this.firstexamSubject = firstexamSubject;
    }

    public String getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(String isApprove) {
        this.isApprove = isApprove;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getJobKind() {
        return jobKind;
    }

    public void setJobKind(String jobKind) {
        this.jobKind = jobKind;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoMemberDTO)) {
            return false;
        }

        GeoMemberDTO geoMemberDTO = (GeoMemberDTO) o;
        if (this.geoMemberId == null) {
            return false;
        }
        return Objects.equals(this.geoMemberId, geoMemberDTO.geoMemberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.geoMemberId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoMemberDTO{" +
                "geoMemberId=" + getGeoMemberId() +
                ", userName='" + getUserName() + "'" +
                ", workTel='" + getWorkTel() + "'" +
                ", addrZip='" + getAddrZip() + "'" +
                ", addrCity='" + getAddrCity() + "'" +
                ", addrTown='" + getAddrTown() + "'" +
                ", addrOther='" + getAddrOther() + "'" +
                ", idno='" + getIdno() + "'" +
                ", userId='" + getUserId() + "'" +
                ", applyDate='" + getApplyDate() + "'" +
                ", jobDemand='" + getJobDemand() + "'" +
                ", sex='" + getSex() + "'" +
                ", birthday='" + getBirthday() + "'" +
                ", email='" + getEmail() + "'" +
                ", homeTel='" + getHomeTel() + "'" +
                ", fax='" + getFax() + "'" +
                ", mobileTel='" + getMobileTel() + "'" +
                ", examineeDegree='" + getExamineeDegree() + "'" +
                ", highestDegree='" + getHighestDegree() + "'" +
                ", firstexamDate='" + getFirstexamDate() + "'" +
                ", firstexamCertNo='" + getFirstexamCertNo() + "'" +
                ", firstexamSubject='" + getFirstexamSubject() + "'" +
                ", isApprove='" + getIsApprove() + "'" +
                ", jobType='" + getJobType() + "'" +
                ", salaryRange='" + getSalaryRange() + "'" +
                ", workPlace='" + getWorkPlace() + "'" +
                ", remark='" + getRemark() + "'" +
                ", isEnable='" + getIsEnable() + "'" +
                ", ranking='" + getRanking() + "'" +
                ", serialNo='" + getSerialNo() + "'" +
                ", jobKind='" + getJobKind() + "'" +
                "}";
    }
}
