package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EngMember} entity.
 */
public class EngMemberDTO implements Serializable {

    @NotNull
    @Size(max = 20)
    private String engMemberId;

    @NotNull
    @Size(max = 1)
    private String type;

    @Size(max = 20)
    private String no;

    @Size(max = 20)
    private String chName;

    @Size(max = 50)
    private String enName;

    @Size(max = 20)
    private String idno;

    private LocalDate birthDate;

    private String sex;

    @Size(max = 100)
    private String tel1;

    @Size(max = 20)
    private String tel2;

    @Size(max = 20)
    private String tel3;

    @Size(max = 20)
    private String fax;

    @Size(max = 10)
    private String permanentAddrZip;

    @Size(max = 5)
    private String permanentAddrCity;

    @Size(max = 3)
    private String permanentAddrTown;

    @Size(max = 100)
    private String permanentAddrOther;

    @Size(max = 10)
    private String contactAddrZip;

    @Size(max = 5)
    private String contactAddrCity;

    @Size(max = 3)
    private String contactAddrTown;

    @Size(max = 100)
    private String contactAddrOther;

    @Size(max = 50)
    private String email1;

    @Size(max = 50)
    private String email2;

    private String isGovMember;

    private String isEngr;

    @Size(max = 1)
    private String identityType;

    @Size(max = 300)
    private String addrForeign;

    @Size(max = 20)
    private String userId;

    @Size(max = 100)
    private String jobTypeList;

    @Size(max = 20)
    private String licenseId;

    @Size(max = 20)
    private String licenseNo;

    @Size(max = 20)
    private String compId;

    @Size(max = 20)
    private String subjectList;

    @Size(max = 20)
    private String execStatus;

    @Size(max = 80)
    private String remark;

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

    public String getEngMemberId() {
        return engMemberId;
    }

    public void setEngMemberId(String engMemberId) {
        this.engMemberId = engMemberId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getTel3() {
        return tel3;
    }

    public void setTel3(String tel3) {
        this.tel3 = tel3;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPermanentAddrZip() {
        return permanentAddrZip;
    }

    public void setPermanentAddrZip(String permanentAddrZip) {
        this.permanentAddrZip = permanentAddrZip;
    }

    public String getPermanentAddrCity() {
        return permanentAddrCity;
    }

    public void setPermanentAddrCity(String permanentAddrCity) {
        this.permanentAddrCity = permanentAddrCity;
    }

    public String getPermanentAddrTown() {
        return permanentAddrTown;
    }

    public void setPermanentAddrTown(String permanentAddrTown) {
        this.permanentAddrTown = permanentAddrTown;
    }

    public String getPermanentAddrOther() {
        return permanentAddrOther;
    }

    public void setPermanentAddrOther(String permanentAddrOther) {
        this.permanentAddrOther = permanentAddrOther;
    }

    public String getContactAddrZip() {
        return contactAddrZip;
    }

    public void setContactAddrZip(String contactAddrZip) {
        this.contactAddrZip = contactAddrZip;
    }

    public String getContactAddrCity() {
        return contactAddrCity;
    }

    public void setContactAddrCity(String contactAddrCity) {
        this.contactAddrCity = contactAddrCity;
    }

    public String getContactAddrTown() {
        return contactAddrTown;
    }

    public void setContactAddrTown(String contactAddrTown) {
        this.contactAddrTown = contactAddrTown;
    }

    public String getContactAddrOther() {
        return contactAddrOther;
    }

    public void setContactAddrOther(String contactAddrOther) {
        this.contactAddrOther = contactAddrOther;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getIsGovMember() {
        return isGovMember;
    }

    public void setIsGovMember(String isGovMember) {
        this.isGovMember = isGovMember;
    }

    public String getIsEngr() {
        return isEngr;
    }

    public void setIsEngr(String isEngr) {
        this.isEngr = isEngr;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getAddrForeign() {
        return addrForeign;
    }

    public void setAddrForeign(String addrForeign) {
        this.addrForeign = addrForeign;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJobTypeList() {
        return jobTypeList;
    }

    public void setJobTypeList(String jobTypeList) {
        this.jobTypeList = jobTypeList;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(String subjectList) {
        this.subjectList = subjectList;
    }

    public String getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(String execStatus) {
        this.execStatus = execStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        if (!(o instanceof EngMemberDTO)) {
            return false;
        }

        EngMemberDTO engMemberDTO = (EngMemberDTO) o;
        if (this.engMemberId == null) {
            return false;
        }
        return Objects.equals(this.engMemberId, engMemberDTO.engMemberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.engMemberId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngMemberDTO{" +
            "engMemberId='" + getEngMemberId() + "'" +
            ", type='" + getType() + "'" +
            ", no='" + getNo() + "'" +
            ", chName='" + getChName() + "'" +
            ", enName='" + getEnName() + "'" +
            ", idno='" + getIdno() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", sex='" + getSex() + "'" +
            ", tel1='" + getTel1() + "'" +
            ", tel2='" + getTel2() + "'" +
            ", tel3='" + getTel3() + "'" +
            ", fax='" + getFax() + "'" +
            ", permanentAddrZip='" + getPermanentAddrZip() + "'" +
            ", permanentAddrCity='" + getPermanentAddrCity() + "'" +
            ", permanentAddrTown='" + getPermanentAddrTown() + "'" +
            ", permanentAddrOther='" + getPermanentAddrOther() + "'" +
            ", contactAddrZip='" + getContactAddrZip() + "'" +
            ", contactAddrCity='" + getContactAddrCity() + "'" +
            ", contactAddrTown='" + getContactAddrTown() + "'" +
            ", contactAddrOther='" + getContactAddrOther() + "'" +
            ", email1='" + getEmail1() + "'" +
            ", email2='" + getEmail2() + "'" +
            ", isGovMember='" + getIsGovMember() + "'" +
            ", isEngr='" + getIsEngr() + "'" +
            ", identityType='" + getIdentityType() + "'" +
            ", addrForeign='" + getAddrForeign() + "'" +
            ", userId='" + getUserId() + "'" +
            ", jobTypeList='" + getJobTypeList() + "'" +
            ", licenseId='" + getLicenseId() + "'" +
            ", licenseNo='" + getLicenseNo() + "'" +
            ", compId='" + getCompId() + "'" +
            ", subjectList='" + getSubjectList() + "'" +
            ", execStatus='" + getExecStatus() + "'" +
            ", remark='" + getRemark() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", transferFrom='" + getTransferFrom() + "'" +
            ", transferTime='" + getTransferTime() + "'" +
            "}";
    }
}
