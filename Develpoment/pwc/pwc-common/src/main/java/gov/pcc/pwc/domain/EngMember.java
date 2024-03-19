package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A EngMember.
 */
@Entity
@Table(name = "eng_member")
public class EngMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "eng_member_id", length = 20, nullable = false)
    private String engMemberId;

    @NotNull
    @Size(max = 1)
    @Column(name = "type", length = 1, nullable = false)
    private String type;

    @Size(max = 20)
    @Column(name = "no", length = 20)
    private String no;

    @Size(max = 20)
    @Column(name = "ch_name", length = 20)
    private String chName;

    @Size(max = 50)
    @Column(name = "en_name", length = 50)
    private String enName;

    @Size(max = 20)
    @Column(name = "idno", length = 20)
    private String idno;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "sex")
    private String sex;

    @Size(max = 100)
    @Column(name = "tel1", length = 100)
    private String tel1;

    @Size(max = 20)
    @Column(name = "tel2", length = 20)
    private String tel2;

    @Size(max = 20)
    @Column(name = "tel3", length = 20)
    private String tel3;

    @Size(max = 20)
    @Column(name = "fax", length = 20)
    private String fax;

    @Size(max = 10)
    @Column(name = "permanent_addr_zip", length = 10)
    private String permanentAddrZip;

    @Size(max = 5)
    @Column(name = "permanent_addr_city", length = 5)
    private String permanentAddrCity;

    @Size(max = 3)
    @Column(name = "permanent_addr_town", length = 3)
    private String permanentAddrTown;

    @Size(max = 100)
    @Column(name = "permanent_addr_other", length = 100)
    private String permanentAddrOther;

    @Size(max = 10)
    @Column(name = "contact_addr_zip", length = 10)
    private String contactAddrZip;

    @Size(max = 5)
    @Column(name = "contact_addr_city", length = 5)
    private String contactAddrCity;

    @Size(max = 3)
    @Column(name = "contact_addr_town", length = 3)
    private String contactAddrTown;

    @Size(max = 100)
    @Column(name = "contact_addr_other", length = 100)
    private String contactAddrOther;

    @Size(max = 50)
    @Column(name = "email_1", length = 50)
    private String email1;

    @Size(max = 50)
    @Column(name = "email_2", length = 50)
    private String email2;

    @Column(name = "is_gov_member")
    private String isGovMember;

    @Column(name = "is_engr")
    private String isEngr;

    @Size(max = 1)
    @Column(name = "identity_type", length = 1)
    private String identityType;

    @Size(max = 300)
    @Column(name = "addr_foreign", length = 300)
    private String addrForeign;

    @Size(max = 20)
    @Column(name = "user_id", length = 20)
    private String userId;

    @Size(max = 100)
    @Column(name = "job_type_list", length = 100)
    private String jobTypeList;

    @Size(max = 20)
    @Column(name = "license_id", length = 20)
    private String licenseId;

    @Size(max = 20)
    @Column(name = "license_no", length = 20)
    private String licenseNo;

    @Size(max = 20)
    @Column(name = "comp_id", length = 20)
    private String compId;

    @Size(max = 20)
    @Column(name = "subject_list", length = 20)
    private String subjectList;

    @Size(max = 20)
    @Column(name = "exec_status", length = 20)
    private String execStatus;

    @Size(max = 80)
    @Column(name = "remark", length = 80)
    private String remark;

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

    public String getEngMemberId() {
        return this.engMemberId;
    }

    public EngMember engMemberId(String engMemberId) {
        this.setEngMemberId(engMemberId);
        return this;
    }

    public void setEngMemberId(String engMemberId) {
        this.engMemberId = engMemberId;
    }

    public String getType() {
        return this.type;
    }

    public EngMember type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNo() {
        return this.no;
    }

    public EngMember no(String no) {
        this.setNo(no);
        return this;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getChName() {
        return this.chName;
    }

    public EngMember chName(String chName) {
        this.setChName(chName);
        return this;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getEnName() {
        return this.enName;
    }

    public EngMember enName(String enName) {
        this.setEnName(enName);
        return this;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getIdno() {
        return this.idno;
    }

    public EngMember idno(String idno) {
        this.setIdno(idno);
        return this;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public EngMember birthDate(LocalDate birthDate) {
        this.setBirthDate(birthDate);
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return this.sex;
    }

    public EngMember sex(String sex) {
        this.setSex(sex);
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel1() {
        return this.tel1;
    }

    public EngMember tel1(String tel1) {
        this.setTel1(tel1);
        return this;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return this.tel2;
    }

    public EngMember tel2(String tel2) {
        this.setTel2(tel2);
        return this;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getTel3() {
        return this.tel3;
    }

    public EngMember tel3(String tel3) {
        this.setTel3(tel3);
        return this;
    }

    public void setTel3(String tel3) {
        this.tel3 = tel3;
    }

    public String getFax() {
        return this.fax;
    }

    public EngMember fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPermanentAddrZip() {
        return this.permanentAddrZip;
    }

    public EngMember permanentAddrZip(String permanentAddrZip) {
        this.setPermanentAddrZip(permanentAddrZip);
        return this;
    }

    public void setPermanentAddrZip(String permanentAddrZip) {
        this.permanentAddrZip = permanentAddrZip;
    }

    public String getPermanentAddrCity() {
        return this.permanentAddrCity;
    }

    public EngMember permanentAddrCity(String permanentAddrCity) {
        this.setPermanentAddrCity(permanentAddrCity);
        return this;
    }

    public void setPermanentAddrCity(String permanentAddrCity) {
        this.permanentAddrCity = permanentAddrCity;
    }

    public String getPermanentAddrTown() {
        return this.permanentAddrTown;
    }

    public EngMember permanentAddrTown(String permanentAddrTown) {
        this.setPermanentAddrTown(permanentAddrTown);
        return this;
    }

    public void setPermanentAddrTown(String permanentAddrTown) {
        this.permanentAddrTown = permanentAddrTown;
    }

    public String getPermanentAddrOther() {
        return this.permanentAddrOther;
    }

    public EngMember permanentAddrOther(String permanentAddrOther) {
        this.setPermanentAddrOther(permanentAddrOther);
        return this;
    }

    public void setPermanentAddrOther(String permanentAddrOther) {
        this.permanentAddrOther = permanentAddrOther;
    }

    public String getContactAddrZip() {
        return this.contactAddrZip;
    }

    public EngMember contactAddrZip(String contactAddrZip) {
        this.setContactAddrZip(contactAddrZip);
        return this;
    }

    public void setContactAddrZip(String contactAddrZip) {
        this.contactAddrZip = contactAddrZip;
    }

    public String getContactAddrCity() {
        return this.contactAddrCity;
    }

    public EngMember contactAddrCity(String contactAddrCity) {
        this.setContactAddrCity(contactAddrCity);
        return this;
    }

    public void setContactAddrCity(String contactAddrCity) {
        this.contactAddrCity = contactAddrCity;
    }

    public String getContactAddrTown() {
        return this.contactAddrTown;
    }

    public EngMember contactAddrTown(String contactAddrTown) {
        this.setContactAddrTown(contactAddrTown);
        return this;
    }

    public void setContactAddrTown(String contactAddrTown) {
        this.contactAddrTown = contactAddrTown;
    }

    public String getContactAddrOther() {
        return this.contactAddrOther;
    }

    public EngMember contactAddrOther(String contactAddrOther) {
        this.setContactAddrOther(contactAddrOther);
        return this;
    }

    public void setContactAddrOther(String contactAddrOther) {
        this.contactAddrOther = contactAddrOther;
    }

    public String getEmail1() {
        return this.email1;
    }

    public EngMember email1(String email1) {
        this.setEmail1(email1);
        return this;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return this.email2;
    }

    public EngMember email2(String email2) {
        this.setEmail2(email2);
        return this;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getIsGovMember() {
        return this.isGovMember;
    }

    public EngMember isGovMember(String isGovMember) {
        this.setIsGovMember(isGovMember);
        return this;
    }

    public void setIsGovMember(String isGovMember) {
        this.isGovMember = isGovMember;
    }

    public String getIsEngr() {
        return this.isEngr;
    }

    public EngMember isEngr(String isEngr) {
        this.setIsEngr(isEngr);
        return this;
    }

    public void setIsEngr(String isEngr) {
        this.isEngr = isEngr;
    }

    public String getIdentityType() {
        return this.identityType;
    }

    public EngMember identityType(String identityType) {
        this.setIdentityType(identityType);
        return this;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getAddrForeign() {
        return this.addrForeign;
    }

    public EngMember addrForeign(String addrForeign) {
        this.setAddrForeign(addrForeign);
        return this;
    }

    public void setAddrForeign(String addrForeign) {
        this.addrForeign = addrForeign;
    }

    public String getUserId() {
        return this.userId;
    }

    public EngMember userId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJobTypeList() {
        return this.jobTypeList;
    }

    public EngMember jobTypeList(String jobTypeList) {
        this.setJobTypeList(jobTypeList);
        return this;
    }

    public void setJobTypeList(String jobTypeList) {
        this.jobTypeList = jobTypeList;
    }

    public String getLicenseId() {
        return this.licenseId;
    }

    public EngMember licenseId(String licenseId) {
        this.setLicenseId(licenseId);
        return this;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getLicenseNo() {
        return this.licenseNo;
    }

    public EngMember licenseNo(String licenseNo) {
        this.setLicenseNo(licenseNo);
        return this;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getCompId() {
        return this.compId;
    }

    public EngMember compId(String compId) {
        this.setCompId(compId);
        return this;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getSubjectList() {
        return this.subjectList;
    }

    public EngMember subjectList(String subjectList) {
        this.setSubjectList(subjectList);
        return this;
    }

    public void setSubjectList(String subjectList) {
        this.subjectList = subjectList;
    }

    public String getExecStatus() {
        return this.execStatus;
    }

    public EngMember execStatus(String execStatus) {
        this.setExecStatus(execStatus);
        return this;
    }

    public void setExecStatus(String execStatus) {
        this.execStatus = execStatus;
    }

    public String getRemark() {
        return this.remark;
    }

    public EngMember remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EngMember updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EngMember updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EngMember createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EngMember createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public EngMember transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return this.transferTime;
    }

    public EngMember transferTime(Instant transferTime) {
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
        if (!(o instanceof EngMember)) {
            return false;
        }
        return engMemberId != null && engMemberId.equals(((EngMember) o).engMemberId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngMember{" +
            "engMemberId=" + getEngMemberId() +
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
