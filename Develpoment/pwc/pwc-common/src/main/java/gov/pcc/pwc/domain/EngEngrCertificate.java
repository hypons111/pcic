package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A EngEngrCertificate.
 */
@Entity
@Table(name = "eng_engr_certificate")
@IdClass(EngEngrCertificatePK.class)
public class EngEngrCertificate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "eng_engr_certificate_id", length = 20, nullable = false)
    private String engEngrCertificateId;

    @NotNull
    @Id
    @Column(name = "eng_engr_certificate_version", precision = 6, scale = 3, nullable = false)
    private BigDecimal engEngrCertificateVersion;

    @Size(max = 20)
    @Column(name = "eng_engr_certificate_no", length = 20)
    private String engEngrCertificateNo;

    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

    @Size(max = 20)
    @Column(name = "review_staff", length = 20)
    private String reviewStaff;

    @Column(name = "review_time")
    private Instant reviewTime;

    @Size(max = 2000)
    @Column(name = "review_desc", length = 2000)
    private String reviewDesc;

    @Size(max = 1)
    @Column(name = "new_from", length = 1)
    private String newFrom;

    @Size(max = 5)
    @Column(name = "apply_items_list", length = 5)
    private String applyItemsList;

    @Size(max = 1)
    @Column(name = "apply_type", length = 1)
    private String applyType;

    @Column(name = "apply_date")
    private LocalDate applyDate;

    @Size(max = 20)
    @Column(name = "engr_id", length = 20)
    private String engrId;

    @Size(max = 20)
    @Column(name = "idno", length = 20)
    private String idno;

    @Size(max = 50)
    @Column(name = "ch_name", length = 50)
    private String chName;

    @Size(max = 50)
    @Column(name = "ch_name_rome", length = 50)
    private String chNameRome;

    @Size(max = 100)
    @Column(name = "en_name", length = 100)
    private String enName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "sex")
    private String sex;

    @Size(max = 20)
    @Column(name = "contact_tel_office", length = 20)
    private String contactTelOffice;

    @Size(max = 20)
    @Column(name = "contact_tel_home", length = 20)
    private String contactTelHome;

    @Size(max = 20)
    @Column(name = "mobile_tel", length = 20)
    private String mobileTel;

    @Size(max = 20)
    @Column(name = "fax", length = 20)
    private String fax;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

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

    @NotNull
    @Size(max = 1)
    @Column(name = "identity_type", length = 1, nullable = false)
    private String identityType;

    @Size(max = 300)
    @Column(name = "addr_foreign", length = 300)
    private String addrForeign;

    @NotNull
    @Size(max = 3)
    @Column(name = "subject", length = 3, nullable = false)
    private String subject;

    @Size(max = 20)
    @Column(name = "engr_certificate_doc_no", length = 20)
    private String engrCertificateDocNo;

    @Size(max = 50)
    @Column(name = "engr_certificate_doc_no_old", length = 50)
    private String engrCertificateDocNoOld;

    @NotNull
    @Size(max = 1)
    @Column(name = "exam_type", length = 1, nullable = false)
    private String examType;

    @Size(max = 100)
    @Column(name = "exam_no", length = 100)
    private String examNo;

    @Size(max = 100)
    @Column(name = "exam_no_old", length = 100)
    private String examNoOld;

    @Column(name = "receive_date")
    private LocalDate receiveDate;

    @Size(max = 20)
    @Column(name = "receive_no", length = 20)
    private String receiveNo;

    @Column(name = "public_date")
    private LocalDate publicDate;

    @Size(max = 1)
    @Column(name = "receive_type", length = 1)
    private String receiveType;

    @Size(max = 10)
    @Column(name = "receive_addr_zip", length = 10)
    private String receiveAddrZip;

    @Size(max = 5)
    @Column(name = "receive_addr_city", length = 5)
    private String receiveAddrCity;

    @Size(max = 3)
    @Column(name = "receive_addr_town", length = 3)
    private String receiveAddrTown;

    @Size(max = 100)
    @Column(name = "receive_addr_other", length = 100)
    private String receiveAddrOther;

    @Size(max = 30)
    @Column(name = "contractor_id", length = 30)
    private String contractorId;

    @Size(max = 1600)
    @Column(name = "remark", length = 1600)
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

    public String getEngEngrCertificateId() {
        return this.engEngrCertificateId;
    }

    public EngEngrCertificate engEngrCertificateId(String engEngrCertificateId) {
        this.setEngEngrCertificateId(engEngrCertificateId);
        return this;
    }

    public void setEngEngrCertificateId(String engEngrCertificateId) {
        this.engEngrCertificateId = engEngrCertificateId;
    }

    public BigDecimal getEngEngrCertificateVersion() {
        return this.engEngrCertificateVersion;
    }

    public EngEngrCertificate engEngrCertificateVersion(BigDecimal engEngrCertificateVersion) {
        this.setEngEngrCertificateVersion(engEngrCertificateVersion);
        return this;
    }

    public void setEngEngrCertificateVersion(BigDecimal engEngrCertificateVersion) {
        this.engEngrCertificateVersion = engEngrCertificateVersion;
    }

    public String getEngEngrCertificateNo() {
        return this.engEngrCertificateNo;
    }

    public EngEngrCertificate engEngrCertificateNo(String engEngrCertificateNo) {
        this.setEngEngrCertificateNo(engEngrCertificateNo);
        return this;
    }

    public void setEngEngrCertificateNo(String engEngrCertificateNo) {
        this.engEngrCertificateNo = engEngrCertificateNo;
    }

    public String getStatus() {
        return this.status;
    }

    public EngEngrCertificate status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReviewStaff() {
        return this.reviewStaff;
    }

    public EngEngrCertificate reviewStaff(String reviewStaff) {
        this.setReviewStaff(reviewStaff);
        return this;
    }

    public void setReviewStaff(String reviewStaff) {
        this.reviewStaff = reviewStaff;
    }

    public Instant getReviewTime() {
        return this.reviewTime;
    }

    public EngEngrCertificate reviewTime(Instant reviewTime) {
        this.setReviewTime(reviewTime);
        return this;
    }

    public void setReviewTime(Instant reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewDesc() {
        return this.reviewDesc;
    }

    public EngEngrCertificate reviewDesc(String reviewDesc) {
        this.setReviewDesc(reviewDesc);
        return this;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc;
    }

    public String getNewFrom() { return this.newFrom; }

    public EngEngrCertificate newFrom(String newFrom) {
        this.setNewFrom(newFrom);
        return this;
    }

    public void setNewFrom(String newFrom) { this.newFrom = newFrom; }

    public String getApplyItemsList() {
        return this.applyItemsList;
    }

    public EngEngrCertificate applyItemsList(String applyItemsList) {
        this.setApplyItemsList(applyItemsList);
        return this;
    }

    public void setApplyItemsList(String applyItemsList) {
        this.applyItemsList = applyItemsList;
    }

    public String getApplyType() {
        return this.applyType;
    }

    public EngEngrCertificate applyType(String applyType) {
        this.setApplyType(applyType);
        return this;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public LocalDate getApplyDate() {
        return this.applyDate;
    }

    public EngEngrCertificate applyDate(LocalDate applyDate) {
        this.setApplyDate(applyDate);
        return this;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public String getEngrId() {
        return this.engrId;
    }

    public EngEngrCertificate engrId(String engrId) {
        this.setEngrId(engrId);
        return this;
    }

    public void setEngrId(String engrId) {
        this.engrId = engrId;
    }

    public String getIdno() {
        return this.idno;
    }

    public EngEngrCertificate idno(String idno) {
        this.setIdno(idno);
        return this;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getChName() {
        return this.chName;
    }

    public EngEngrCertificate chName(String chName) {
        this.setChName(chName);
        return this;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getChNameRome() {
        return this.chNameRome;
    }

    public EngEngrCertificate chNameRome(String chNameRome) {
        this.setChNameRome(chNameRome);
        return this;
    }

    public void setChNameRome(String chNameRome) {
        this.chNameRome = chNameRome;
    }

    public String getEnName() {
        return this.enName;
    }

    public EngEngrCertificate enName(String enName) {
        this.setEnName(enName);
        return this;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public EngEngrCertificate birthDate(LocalDate birthDate) {
        this.setBirthDate(birthDate);
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return this.sex;
    }

    public EngEngrCertificate sex(String sex) {
        this.setSex(sex);
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContactTelOffice() {
        return this.contactTelOffice;
    }

    public EngEngrCertificate contactTelOffice(String contactTelOffice) {
        this.setContactTelOffice(contactTelOffice);
        return this;
    }

    public void setContactTelOffice(String contactTelOffice) {
        this.contactTelOffice = contactTelOffice;
    }

    public String getContactTelHome() {
        return this.contactTelHome;
    }

    public EngEngrCertificate contactTelHome(String contactTelHome) {
        this.setContactTelHome(contactTelHome);
        return this;
    }

    public void setContactTelHome(String contactTelHome) {
        this.contactTelHome = contactTelHome;
    }

    public String getMobileTel() {
        return this.mobileTel;
    }

    public EngEngrCertificate mobileTel(String mobileTel) {
        this.setMobileTel(mobileTel);
        return this;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getFax() {
        return this.fax;
    }

    public EngEngrCertificate fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return this.email;
    }

    public EngEngrCertificate email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermanentAddrZip() {
        return this.permanentAddrZip;
    }

    public EngEngrCertificate permanentAddrZip(String permanentAddrZip) {
        this.setPermanentAddrZip(permanentAddrZip);
        return this;
    }

    public void setPermanentAddrZip(String permanentAddrZip) {
        this.permanentAddrZip = permanentAddrZip;
    }

    public String getPermanentAddrCity() {
        return this.permanentAddrCity;
    }

    public EngEngrCertificate permanentAddrCity(String permanentAddrCity) {
        this.setPermanentAddrCity(permanentAddrCity);
        return this;
    }

    public void setPermanentAddrCity(String permanentAddrCity) {
        this.permanentAddrCity = permanentAddrCity;
    }

    public String getPermanentAddrTown() {
        return this.permanentAddrTown;
    }

    public EngEngrCertificate permanentAddrTown(String permanentAddrTown) {
        this.setPermanentAddrTown(permanentAddrTown);
        return this;
    }

    public void setPermanentAddrTown(String permanentAddrTown) {
        this.permanentAddrTown = permanentAddrTown;
    }

    public String getPermanentAddrOther() {
        return this.permanentAddrOther;
    }

    public EngEngrCertificate permanentAddrOther(String permanentAddrOther) {
        this.setPermanentAddrOther(permanentAddrOther);
        return this;
    }

    public void setPermanentAddrOther(String permanentAddrOther) {
        this.permanentAddrOther = permanentAddrOther;
    }

    public String getContactAddrZip() {
        return this.contactAddrZip;
    }

    public EngEngrCertificate contactAddrZip(String contactAddrZip) {
        this.setContactAddrZip(contactAddrZip);
        return this;
    }

    public void setContactAddrZip(String contactAddrZip) {
        this.contactAddrZip = contactAddrZip;
    }

    public String getContactAddrCity() {
        return this.contactAddrCity;
    }

    public EngEngrCertificate contactAddrCity(String contactAddrCity) {
        this.setContactAddrCity(contactAddrCity);
        return this;
    }

    public void setContactAddrCity(String contactAddrCity) {
        this.contactAddrCity = contactAddrCity;
    }

    public String getContactAddrTown() {
        return this.contactAddrTown;
    }

    public EngEngrCertificate contactAddrTown(String contactAddrTown) {
        this.setContactAddrTown(contactAddrTown);
        return this;
    }

    public void setContactAddrTown(String contactAddrTown) {
        this.contactAddrTown = contactAddrTown;
    }

    public String getContactAddrOther() {
        return this.contactAddrOther;
    }

    public EngEngrCertificate contactAddrOther(String contactAddrOther) {
        this.setContactAddrOther(contactAddrOther);
        return this;
    }

    public void setContactAddrOther(String contactAddrOther) {
        this.contactAddrOther = contactAddrOther;
    }

    public String getIdentityType() {
        return this.identityType;
    }

    public EngEngrCertificate identityType(String identityType) {
        this.setIdentityType(identityType);
        return this;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getAddrForeign() {
        return this.addrForeign;
    }

    public EngEngrCertificate addrForeign(String addrForeign) {
        this.setAddrForeign(addrForeign);
        return this;
    }

    public void setAddrForeign(String addrForeign) {
        this.addrForeign = addrForeign;
    }

    public String getSubject() {
        return this.subject;
    }

    public EngEngrCertificate subject(String subject) {
        this.setSubject(subject);
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEngrCertificateDocNo() {
        return this.engrCertificateDocNo;
    }

    public EngEngrCertificate engrCertificateDocNo(String engrCertificateDocNo) {
        this.setEngrCertificateDocNo(engrCertificateDocNo);
        return this;
    }

    public void setEngrCertificateDocNo(String engrCertificateDocNo) {
        this.engrCertificateDocNo = engrCertificateDocNo;
    }

    public String getEngrCertificateDocNoOld() {
        return this.engrCertificateDocNoOld;
    }

    public EngEngrCertificate engrCertificateDocNoOld(String engrCertificateDocNoOld) {
        this.setEngrCertificateDocNoOld(engrCertificateDocNoOld);
        return this;
    }

    public void setEngrCertificateDocNoOld(String engrCertificateDocNoOld) {
        this.engrCertificateDocNoOld = engrCertificateDocNoOld;
    }

    public String getExamType() {
        return this.examType;
    }

    public EngEngrCertificate examType(String examType) {
        this.setExamType(examType);
        return this;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getExamNo() {
        return this.examNo;
    }

    public EngEngrCertificate examNo(String examNo) {
        this.setExamNo(examNo);
        return this;
    }

    public void setExamNo(String examNo) {
        this.examNo = examNo;
    }

    public String getExamNoOld() {
        return this.examNoOld;
    }

    public EngEngrCertificate examNoOld(String examNoOld) {
        this.setExamNoOld(examNoOld);
        return this;
    }

    public void setExamNoOld(String examNoOld) {
        this.examNoOld = examNoOld;
    }

    public LocalDate getReceiveDate() {
        return this.receiveDate;
    }

    public EngEngrCertificate receiveDate(LocalDate receiveDate) {
        this.setReceiveDate(receiveDate);
        return this;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReceiveNo() {
        return this.receiveNo;
    }

    public EngEngrCertificate receiveNo(String receiveNo) {
        this.setReceiveNo(receiveNo);
        return this;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public LocalDate getPublicDate() {
        return this.publicDate;
    }

    public EngEngrCertificate publicDate(LocalDate publicDate) {
        this.setPublicDate(publicDate);
        return this;
    }

    public void setPublicDate(LocalDate publicDate) {
        this.publicDate = publicDate;
    }

    public String getReceiveType() {
        return this.receiveType;
    }

    public EngEngrCertificate receiveType(String receiveType) {
        this.setReceiveType(receiveType);
        return this;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public String getReceiveAddrZip() {
        return this.receiveAddrZip;
    }

    public EngEngrCertificate receiveAddrZip(String receiveAddrZip) {
        this.setReceiveAddrZip(receiveAddrZip);
        return this;
    }

    public void setReceiveAddrZip(String receiveAddrZip) {
        this.receiveAddrZip = receiveAddrZip;
    }

    public String getReceiveAddrCity() {
        return this.receiveAddrCity;
    }

    public EngEngrCertificate receiveAddrCity(String receiveAddrCity) {
        this.setReceiveAddrCity(receiveAddrCity);
        return this;
    }

    public void setReceiveAddrCity(String receiveAddrCity) {
        this.receiveAddrCity = receiveAddrCity;
    }

    public String getReceiveAddrTown() {
        return this.receiveAddrTown;
    }

    public EngEngrCertificate receiveAddrTown(String receiveAddrTown) {
        this.setReceiveAddrTown(receiveAddrTown);
        return this;
    }

    public void setReceiveAddrTown(String receiveAddrTown) {
        this.receiveAddrTown = receiveAddrTown;
    }

    public String getReceiveAddrOther() {
        return this.receiveAddrOther;
    }

    public EngEngrCertificate receiveAddrOther(String receiveAddrOther) {
        this.setReceiveAddrOther(receiveAddrOther);
        return this;
    }

    public void setReceiveAddrOther(String receiveAddrOther) {
        this.receiveAddrOther = receiveAddrOther;
    }

    public String getContractorId() {
        return this.contractorId;
    }

    public EngEngrCertificate contractorId(String contractorId) {
        this.setContractorId(contractorId);
        return this;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getRemark() {
        return this.remark;
    }

    public EngEngrCertificate remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EngEngrCertificate updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EngEngrCertificate updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EngEngrCertificate createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EngEngrCertificate createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public EngEngrCertificate transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return this.transferTime;
    }

    public EngEngrCertificate transferTime(Instant transferTime) {
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
        if (!(o instanceof EngEngrCertificate)) {
            return false;
        }
        return engEngrCertificateId != null && engEngrCertificateId.equals(((EngEngrCertificate) o).engEngrCertificateId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngEngrCertificate{" +
            "engEngrCertificateId=" + getEngEngrCertificateId() +
            ", engEngrCertificateVersion=" + getEngEngrCertificateVersion() +
            ", engEngrCertificateNo='" + getEngEngrCertificateNo() + "'" +
            ", status='" + getStatus() + "'" +
            ", reviewStaff='" + getReviewStaff() + "'" +
            ", reviewTime='" + getReviewTime() + "'" +
            ", reviewDesc='" + getReviewDesc() + "'" +
            ", newFrom='" + getNewFrom() + "'" +
            ", applyItemsList='" + getApplyItemsList() + "'" +
            ", applyType='" + getApplyType() + "'" +
            ", applyDate='" + getApplyDate() + "'" +
            ", engrId='" + getEngrId() + "'" +
            ", idno='" + getIdno() + "'" +
            ", chName='" + getChName() + "'" +
            ", chNameRome='" + getChNameRome() + "'" +
            ", enName='" + getEnName() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", sex='" + getSex() + "'" +
            ", contactTelOffice='" + getContactTelOffice() + "'" +
            ", contactTelHome='" + getContactTelHome() + "'" +
            ", mobileTel='" + getMobileTel() + "'" +
            ", fax='" + getFax() + "'" +
            ", email='" + getEmail() + "'" +
            ", permanentAddrZip='" + getPermanentAddrZip() + "'" +
            ", permanentAddrCity='" + getPermanentAddrCity() + "'" +
            ", permanentAddrTown='" + getPermanentAddrTown() + "'" +
            ", permanentAddrOther='" + getPermanentAddrOther() + "'" +
            ", contactAddrZip='" + getContactAddrZip() + "'" +
            ", contactAddrCity='" + getContactAddrCity() + "'" +
            ", contactAddrTown='" + getContactAddrTown() + "'" +
            ", contactAddrOther='" + getContactAddrOther() + "'" +
            ", identityType='" + getIdentityType() + "'" +
            ", addrForeign='" + getAddrForeign() + "'" +
            ", subject='" + getSubject() + "'" +
            ", engrCertificateDocNo='" + getEngrCertificateDocNo() + "'" +
            ", engrCertificateDocNoOld='" + getEngrCertificateDocNoOld() + "'" +
            ", examType='" + getExamType() + "'" +
            ", examNo='" + getExamNo() + "'" +
            ", examNoOld='" + getExamNoOld() + "'" +
            ", receiveDate='" + getReceiveDate() + "'" +
            ", receiveNo='" + getReceiveNo() + "'" +
            ", publicDate='" + getPublicDate() + "'" +
            ", receiveType='" + getReceiveType() + "'" +
            ", receiveAddrZip='" + getReceiveAddrZip() + "'" +
            ", receiveAddrCity='" + getReceiveAddrCity() + "'" +
            ", receiveAddrTown='" + getReceiveAddrTown() + "'" +
            ", receiveAddrOther='" + getReceiveAddrOther() + "'" +
            ", contractorId='" + getContractorId() + "'" +
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
