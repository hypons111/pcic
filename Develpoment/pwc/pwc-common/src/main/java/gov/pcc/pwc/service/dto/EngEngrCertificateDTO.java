package gov.pcc.pwc.service.dto;

import com.google.common.base.MoreObjects;
import gov.pcc.pwc.domain.AdmFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EngEngrCertificate} entity.
 */
public class EngEngrCertificateDTO implements Serializable {

    @Size(max = 20)
    private String engEngrCertificateId;

    private BigDecimal engEngrCertificateVersion;

    @Size(max = 20)
    private String engEngrCertificateNo;

    @Size(max = 1)
    private String status;

    @Size(max = 20)
    private String reviewStaff;

    private Instant reviewTime;

    @Size(max = 2000)
    private String reviewDesc;

    @Size(max = 1)
    private String newFrom;

    @Size(max = 5)
    private String applyItemsList;

    @Size(max = 1)
    private String applyType;

    private LocalDate applyDate;

    @Size(max = 20)
    private String engrId;

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

    @Size(max = 20)
    private String contactTelOffice;

    @Size(max = 20)
    private String contactTelHome;

    @Size(max = 20)
    private String mobileTel;

    @Size(max = 20)
    private String fax;

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

    @NotNull
    @Size(max = 1)
    private String identityType;

    @Size(max = 300)
    private String addrForeign;

    @NotNull
    @Size(max = 3)
    private String subject;

    @Size(max = 20)
    private String engrCertificateDocNo;

    @Size(max = 50)
    private String engrCertificateDocNoOld;

    @NotNull
    @Size(max = 1)
    private String examType;

    @Size(max = 100)
    private String examNo;

    @Size(max = 100)
    private String examNoOld;

    private LocalDate receiveDate;

    @Size(max = 20)
    private String receiveNo;

    private LocalDate publicDate;

    @Size(max = 1)
    private String receiveType;

    @Size(max = 10)
    private String receiveAddrZip;

    @Size(max = 5)
    private String receiveAddrCity;

    @Size(max = 3)
    private String receiveAddrTown;

    @Size(max = 100)
    private String receiveAddrOther;

    @Size(max = 30)
    private String contractorId;

    @Size(max = 1600)
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

    private AdmFile picFileInfo;

    private List<AdmFile> appendixFilesInfo;

    public String getEngEngrCertificateId() {
        return engEngrCertificateId;
    }

    public void setEngEngrCertificateId(String engEngrCertificateId) {
        this.engEngrCertificateId = engEngrCertificateId;
    }

    public BigDecimal getEngEngrCertificateVersion() {
        return engEngrCertificateVersion;
    }

    public void setEngEngrCertificateVersion(BigDecimal engEngrCertificateVersion) {
        this.engEngrCertificateVersion = engEngrCertificateVersion;
    }

    public String getEngEngrCertificateNo() {
        return engEngrCertificateNo;
    }

    public void setEngEngrCertificateNo(String engEngrCertificateNo) {
        this.engEngrCertificateNo = engEngrCertificateNo;
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

    public String getNewFrom() {
        return newFrom;
    }

    public void setNewFrom(String newFrom) {
        this.newFrom = newFrom;
    }

    public String getApplyItemsList() {
        return applyItemsList;
    }

    public void setApplyItemsList(String applyItemsList) {
        this.applyItemsList = applyItemsList;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public String getEngrId() {
        return engrId;
    }

    public void setEngrId(String engrId) {
        this.engrId = engrId;
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

    public String getContactTelOffice() {
        return contactTelOffice;
    }

    public void setContactTelOffice(String contactTelOffice) {
        this.contactTelOffice = contactTelOffice;
    }

    public String getContactTelHome() {
        return contactTelHome;
    }

    public void setContactTelHome(String contactTelHome) {
        this.contactTelHome = contactTelHome;
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEngrCertificateDocNo() {
        return engrCertificateDocNo;
    }

    public void setEngrCertificateDocNo(String engrCertificateDocNo) {
        this.engrCertificateDocNo = engrCertificateDocNo;
    }

    public String getEngrCertificateDocNoOld() {
        return engrCertificateDocNoOld;
    }

    public void setEngrCertificateDocNoOld(String engrCertificateDocNoOld) {
        this.engrCertificateDocNoOld = engrCertificateDocNoOld;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getExamNo() {
        return examNo;
    }

    public void setExamNo(String examNo) {
        this.examNo = examNo;
    }

    public String getExamNoOld() {
        return examNoOld;
    }

    public void setExamNoOld(String examNoOld) {
        this.examNoOld = examNoOld;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
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

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
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

    public AdmFile getPicFileInfo() {
        return picFileInfo;
    }

    public void setPicFileInfo(AdmFile picFileInfo) {
        this.picFileInfo = picFileInfo;
    }

    public List<AdmFile> getAppendixFilesInfo() {
        return appendixFilesInfo;
    }

    public void setAppendixFilesInfo(List<AdmFile> appendixFilesInfo) {
        this.appendixFilesInfo = appendixFilesInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngEngrCertificateDTO)) {
            return false;
        }

        EngEngrCertificateDTO engEngrCertificateDTO = (EngEngrCertificateDTO) o;
        if (this.engEngrCertificateId == null) {
            return false;
        }
        return Objects.equals(this.engEngrCertificateId, engEngrCertificateDTO.engEngrCertificateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.engEngrCertificateId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("engEngrCertificateId", engEngrCertificateId)
                .add("engEngrCertificateVersion", engEngrCertificateVersion)
                .add("engEngrCertificateNo", engEngrCertificateNo)
                .add("status", status)
                .add("reviewStaff", reviewStaff)
                .add("reviewTime", reviewTime)
                .add("reviewDesc", reviewDesc)
                .add("newFrom", newFrom)
                .add("applyItemsList", applyItemsList)
                .add("applyType", applyType)
                .add("applyDate", applyDate)
                .add("engrId", engrId)
                .add("idno", idno)
                .add("chName", chName)
                .add("chNameRome", chNameRome)
                .add("enName", enName)
                .add("birthDate", birthDate)
                .add("sex", sex)
                .add("contactTelOffice", contactTelOffice)
                .add("contactTelHome", contactTelHome)
                .add("mobileTel", mobileTel)
                .add("fax", fax)
                .add("email", email)
                .add("permanentAddrZip", permanentAddrZip)
                .add("permanentAddrCity", permanentAddrCity)
                .add("permanentAddrTown", permanentAddrTown)
                .add("permanentAddrOther", permanentAddrOther)
                .add("contactAddrZip", contactAddrZip)
                .add("contactAddrCity", contactAddrCity)
                .add("contactAddrTown", contactAddrTown)
                .add("contactAddrOther", contactAddrOther)
                .add("identityType", identityType)
                .add("addrForeign", addrForeign)
                .add("subject", subject)
                .add("engrCertificateDocNo", engrCertificateDocNo)
                .add("engrCertificateDocNoOld", engrCertificateDocNoOld)
                .add("examType", examType)
                .add("examNo", examNo)
                .add("examNoOld", examNoOld)
                .add("receiveDate", receiveDate)
                .add("receiveNo", receiveNo)
                .add("publicDate", publicDate)
                .add("receiveType", receiveType)
                .add("receiveAddrZip", receiveAddrZip)
                .add("receiveAddrCity", receiveAddrCity)
                .add("receiveAddrTown", receiveAddrTown)
                .add("receiveAddrOther", receiveAddrOther)
                .add("contractorId", contractorId)
                .add("remark", remark)
                .add("updateTime", updateTime)
                .add("updateUser", updateUser)
                .add("createTime", createTime)
                .add("createUser", createUser)
                .add("transferFrom", transferFrom)
                .add("transferTime", transferTime)
                .add("picFileInfo", picFileInfo)
                .add("appendixFilesInfo", appendixFilesInfo)
                .toString();
    }

//    public StatusEnum getStatusEnum() {
//        return EnumUtil.getByCode(status, StatusEnum.class);
//    }
}
