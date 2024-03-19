package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EngEngrLicense} entity.
 */
public class EngEngrLicenseDTO implements Serializable {

    @NotNull
    @Size(max = 20)
    private String engEngrLicenseId;

    @NotNull
    private BigDecimal engEngrLicenseVersion;

    @Size(max = 20)
    private String engEngrLicenseNo;

    private LocalDate receiveDate;

    @Size(max = 20)
    private String engrLicenseDocNo;

    @Size(max = 20)
    private String engrId;

    @Size(max = 3)
    private String identityType;

    @Size(max = 20)
    private String idno;

    @Size(max = 50)
    private String chName;

    @Size(max = 50)
    private String chNameRome;

    @Size(max = 100)
    private String enName;

    private LocalDate birthDate;

    private String sex;

    @Size(max = 50)
    private String mobileTel;

    @Size(max = 50)
    private String email;

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

    @Size(max = 3)
    private String execType;

    @Size(max = 20)
    private String compId;

    @Size(max = 3)
    private String compType;

    @Size(max = 20)
    private String compIdno;

    @Size(max = 100)
    private String compChName;

    @Size(max = 200)
    private String compEnName;

    @Size(max = 20)
    private String compTel1;

    @Size(max = 20)
    private String compTel2;

    @Size(max = 20)
    private String compFax;

    @Size(max = 10)
    private String compAddrZip;

    @Size(max = 5)
    private String compAddrCity;

    @Size(max = 3)
    private String compAddrTown;

    @Size(max = 100)
    private String compAddrOther;

    @Size(max = 200)
    private String compAddrEn;

    @Size(max = 20)
    private String applyItem;

    @Size(max = 3)
    private String applyType;

    @Size(max = 20)
    private String receiveNo;

    private LocalDate publicDate;

    private LocalDate effectDateStart;

    private LocalDate effectDateEnd;

    @Size(max = 2000)
    private String remark;

    @Size(max = 3)
    private String receiveType;

    @Size(max = 10)
    private String receiveAddrZip;

    @Size(max = 5)
    private String receiveAddrCity;

    @Size(max = 3)
    private String receiveAddrTown;

    @Size(max = 100)
    private String receiveAddrOther;

    @Size(max = 200)
    private String certificateIdList;

    @Size(max = 100)
    private String subjectList;

    private String isMore7year;

    private String isXcaCert;

    @Size(max = 100)
    private String changeItems;

    @Size(max = 400)
    private String changeItemsDesc;

    @Size(max = 20)
    private String contractorId;

    @Size(max = 3)
    private String status;

    @Size(max = 20)
    private String reviewStaff;

    private Instant reviewTime;

    @Size(max = 2000)
    private String reviewDesc;

    @Size(max = 20)
    private String createUser;

    private Instant createTime;

    @Size(max = 20)
    private String updateUser;

    private Instant updateTime;

    @Size(max = 100)
    private String transferFrom;

    private Instant transferTime;

    private LocalDate reciveDate;
    private int sal;
    public String getEngEngrLicenseId() {
        return engEngrLicenseId;
    }

    public void setEngEngrLicenseId(String engEngrLicenseId) {
        this.engEngrLicenseId = engEngrLicenseId;
    }

    public BigDecimal getEngEngrLicenseVersion() {
        return engEngrLicenseVersion;
    }

    public void setEngEngrLicenseVersion(BigDecimal engEngrLicenseVersion) {
        this.engEngrLicenseVersion = engEngrLicenseVersion;
    }

    public String getEngEngrLicenseNo() {
        return engEngrLicenseNo;
    }

    public void setEngEngrLicenseNo(String engEngrLicenseNo) {
        this.engEngrLicenseNo = engEngrLicenseNo;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getEngrLicenseDocNo() {
        return engrLicenseDocNo;
    }

    public void setEngrLicenseDocNo(String engrLicenseDocNo) {
        this.engrLicenseDocNo = engrLicenseDocNo;
    }

    public String getEngrId() {
        return engrId;
    }

    public void setEngrId(String engrId) {
        this.engrId = engrId;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getChNameRome() {
        return chNameRome;
    }

    public void setChNameRome(String chNameRome) {
        this.chNameRome = chNameRome;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
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

    public String getMobileTel() {
        return mobileTel;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getExecType() {
        return execType;
    }

    public void setExecType(String execType) {
        this.execType = execType;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getCompType() {
        return compType;
    }

    public void setCompType(String compType) {
        this.compType = compType;
    }

    public String getCompIdno() {
        return compIdno;
    }

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
    }

    public String getCompChName() {
        return compChName;
    }

    public void setCompChName(String compChName) {
        this.compChName = compChName;
    }

    public String getCompEnName() {
        return compEnName;
    }

    public void setCompEnName(String compEnName) {
        this.compEnName = compEnName;
    }

    public String getCompTel1() {
        return compTel1;
    }

    public void setCompTel1(String compTel1) {
        this.compTel1 = compTel1;
    }

    public String getCompTel2() {
        return compTel2;
    }

    public void setCompTel2(String compTel2) {
        this.compTel2 = compTel2;
    }

    public String getCompFax() {
        return compFax;
    }

    public void setCompFax(String compFax) {
        this.compFax = compFax;
    }

    public String getCompAddrZip() {
        return compAddrZip;
    }

    public void setCompAddrZip(String compAddrZip) {
        this.compAddrZip = compAddrZip;
    }

    public String getCompAddrCity() {
        return compAddrCity;
    }

    public void setCompAddrCity(String compAddrCity) {
        this.compAddrCity = compAddrCity;
    }

    public String getCompAddrTown() {
        return compAddrTown;
    }

    public void setCompAddrTown(String compAddrTown) {
        this.compAddrTown = compAddrTown;
    }

    public String getCompAddrOther() {
        return compAddrOther;
    }

    public void setCompAddrOther(String compAddrOther) {
        this.compAddrOther = compAddrOther;
    }

    public String getCompAddrEn() {
        return compAddrEn;
    }

    public void setCompAddrEn(String compAddrEn) {
        this.compAddrEn = compAddrEn;
    }

    public String getApplyItem() {
        return applyItem;
    }

    public void setApplyItem(String applyItem) {
        this.applyItem = applyItem;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public LocalDate getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(LocalDate publicDate) {
        this.publicDate = publicDate;
    }

    public LocalDate getEffectDateStart() {
        return effectDateStart;
    }

    public void setEffectDateStart(LocalDate effectDateStart) {
        this.effectDateStart = effectDateStart;
    }

    public LocalDate getEffectDateEnd() {
        return effectDateEnd;
    }

    public void setEffectDateEnd(LocalDate effectDateEnd) {
        this.effectDateEnd = effectDateEnd;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public String getReceiveAddrZip() {
        return receiveAddrZip;
    }

    public void setReceiveAddrZip(String receiveAddrZip) {
        this.receiveAddrZip = receiveAddrZip;
    }

    public String getReceiveAddrCity() {
        return receiveAddrCity;
    }

    public void setReceiveAddrCity(String receiveAddrCity) {
        this.receiveAddrCity = receiveAddrCity;
    }

    public String getReceiveAddrTown() {
        return receiveAddrTown;
    }

    public void setReceiveAddrTown(String receiveAddrTown) {
        this.receiveAddrTown = receiveAddrTown;
    }

    public String getReceiveAddrOther() {
        return receiveAddrOther;
    }

    public void setReceiveAddrOther(String receiveAddrOther) {
        this.receiveAddrOther = receiveAddrOther;
    }

    public String getCertificateIdList() {
        return certificateIdList;
    }

    public void setCertificateIdList(String certificateIdList) {
        this.certificateIdList = certificateIdList;
    }

    public String getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(String subjectList) {
        this.subjectList = subjectList;
    }

    public String getIsMore7year() {
        return isMore7year;
    }

    public void setIsMore7year(String isMore7year) {
        this.isMore7year = isMore7year;
    }

    public String getIsXcaCert() {
        return isXcaCert;
    }

    public void setIsXcaCert(String isXcaCert) {
        this.isXcaCert = isXcaCert;
    }

    public String getChangeItems() {
        return changeItems;
    }

    public void setChangeItems(String changeItems) {
        this.changeItems = changeItems;
    }

    public String getChangeItemsDesc() {
        return changeItemsDesc;
    }

    public void setChangeItemsDesc(String changeItemsDesc) {
        this.changeItemsDesc = changeItemsDesc;
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReviewStaff() {
        return reviewStaff;
    }

    public void setReviewStaff(String reviewStaff) {
        this.reviewStaff = reviewStaff;
    }

    public Instant getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Instant reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewDesc() {
        return reviewDesc;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
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

    public LocalDate getReciveDate() {
        return reciveDate;
    }

    public void setReciveDate(LocalDate reciveDate) {
        this.reciveDate = reciveDate;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngEngrLicenseDTO)) {
            return false;
        }

        EngEngrLicenseDTO engEngrLicenseDTO = (EngEngrLicenseDTO) o;
        if (this.engEngrLicenseId == null) {
            return false;
        }
        return Objects.equals(this.engEngrLicenseId, engEngrLicenseDTO.engEngrLicenseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.engEngrLicenseId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngEngrLicenseDTO{" +
            "engEngrLicenseId='" + getEngEngrLicenseId() + "'" +
            ", engEngrLicenseVersion=" + getEngEngrLicenseVersion() +
            ", engEngrLicenseNo='" + getEngEngrLicenseNo() + "'" +
            ", receiveDate='" + getReceiveDate() + "'" +
            ", engrLicenseDocNo='" + getEngrLicenseDocNo() + "'" +
            ", engrId='" + getEngrId() + "'" +
            ", identityType='" + getIdentityType() + "'" +
            ", idno='" + getIdno() + "'" +
            ", chName='" + getChName() + "'" +
            ", chNameRome='" + getChNameRome() + "'" +
            ", enName='" + getEnName() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", sex='" + getSex() + "'" +
            ", mobileTel='" + getMobileTel() + "'" +
            ", email='" + getEmail() + "'" +
            ", permanentAddrZip='" + getPermanentAddrZip() + "'" +
            ", permanentAddrCity='" + getPermanentAddrCity() + "'" +
            ", permanentAddrTown='" + getPermanentAddrTown() + "'" +
            ", permanentAddrOther='" + getPermanentAddrOther() + "'" +
            ", contactAddrZip='" + getContactAddrZip() + "'" +
            ", contactAddrCity='" + getContactAddrCity() + "'" +
            ", contactAddrTown='" + getContactAddrTown() + "'" +
            ", contactAddrOther='" + getContactAddrOther() + "'" +
            ", execType='" + getExecType() + "'" +
            ", compId='" + getCompId() + "'" +
            ", compType='" + getCompType() + "'" +
            ", compIdno='" + getCompIdno() + "'" +
            ", compChName='" + getCompChName() + "'" +
            ", compEnName='" + getCompEnName() + "'" +
            ", compTel1='" + getCompTel1() + "'" +
            ", compTel2='" + getCompTel2() + "'" +
            ", compFax='" + getCompFax() + "'" +
            ", compAddrZip='" + getCompAddrZip() + "'" +
            ", compAddrCity='" + getCompAddrCity() + "'" +
            ", compAddrTown='" + getCompAddrTown() + "'" +
            ", compAddrOther='" + getCompAddrOther() + "'" +
            ", compAddrEn='" + getCompAddrEn() + "'" +
            ", applyItem='" + getApplyItem() + "'" +
            ", applyType='" + getApplyType() + "'" +
            ", receiveNo='" + getReceiveNo() + "'" +
            ", publicDate='" + getPublicDate() + "'" +
            ", effectDateStart='" + getEffectDateStart() + "'" +
            ", effectDateEnd='" + getEffectDateEnd() + "'" +
            ", remark='" + getRemark() + "'" +
            ", receiveType='" + getReceiveType() + "'" +
            ", receiveAddrZip='" + getReceiveAddrZip() + "'" +
            ", receiveAddrCity='" + getReceiveAddrCity() + "'" +
            ", receiveAddrTown='" + getReceiveAddrTown() + "'" +
            ", receiveAddrOther='" + getReceiveAddrOther() + "'" +
            ", certificateIdList='" + getCertificateIdList() + "'" +
            ", subjectList='" + getSubjectList() + "'" +
            ", isMore7year='" + getIsMore7year() + "'" +
            ", isXcaCert='" + getIsXcaCert() + "'" +
            ", changeItems='" + getChangeItems() + "'" +
            ", changeItemsDesc='" + getChangeItemsDesc() + "'" +
            ", contractorId='" + getContractorId() + "'" +
            ", status='" + getStatus() + "'" +
            ", reviewStaff='" + getReviewStaff() + "'" +
            ", reviewTime='" + getReviewTime() + "'" +
            ", reviewDesc='" + getReviewDesc() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", transferFrom='" + getTransferFrom() + "'" +
            ", transferTime='" + getTransferTime() + "'" +
                ", reciveDate='" + getReciveDate() + "'" +
                ", sal='" + getSal() + "'" +
            "}";
    }
}
