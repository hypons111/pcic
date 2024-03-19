package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A EngEngrLicense.
 */
@Entity
@Table(name = "eng_engr_license")
@IdClass(EngEngrLicensePK.class)
public class EngEngrLicense implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "eng_engr_license_id", length = 20, nullable = false)
    private String engEngrLicenseId;

    @NotNull
    @Id
    @Column(name = "eng_engr_license_version", precision = 21, scale = 2, nullable = false)
    private BigDecimal engEngrLicenseVersion;

    @Size(max = 20)
    @Column(name = "eng_engr_license_no", length = 20)
    private String engEngrLicenseNo;

    @Column(name = "receive_date")
    private LocalDate receiveDate;

    @Column(name = "RECIVE_DATE")
    private LocalDate reciveDate;

    @Size(max = 20)
    @Column(name = "engr_license_doc_no", length = 20)
    private String engrLicenseDocNo;

    @Size(max = 20)
    @Column(name = "engr_id", length = 20)
    private String engrId;

    @Size(max = 3)
    @Column(name = "identity_type", length = 3)
    private String identityType;

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

    @Size(max = 50)
    @Column(name = "mobile_tel", length = 50)
    private String mobileTel;

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

    @Size(max = 3)
    @Column(name = "exec_type", length = 3)
    private String execType;

    @Size(max = 20)
    @Column(name = "comp_id", length = 20)
    private String compId;

    @Size(max = 3)
    @Column(name = "comp_type", length = 3)
    private String compType;

    @Size(max = 20)
    @Column(name = "comp_idno", length = 20)
    private String compIdno;

    @Size(max = 100)
    @Column(name = "comp_ch_name", length = 100)
    private String compChName;

    @Size(max = 200)
    @Column(name = "comp_en_name", length = 200)
    private String compEnName;

    @Size(max = 20)
    @Column(name = "comp_tel_1", length = 20)
    private String compTel1;

    @Size(max = 20)
    @Column(name = "comp_tel_2", length = 20)
    private String compTel2;

    @Size(max = 20)
    @Column(name = "comp_fax", length = 20)
    private String compFax;

    @Size(max = 10)
    @Column(name = "comp_addr_zip", length = 10)
    private String compAddrZip;

    @Size(max = 5)
    @Column(name = "comp_addr_city", length = 5)
    private String compAddrCity;

    @Size(max = 3)
    @Column(name = "comp_addr_town", length = 3)
    private String compAddrTown;

    @Size(max = 100)
    @Column(name = "comp_addr_other", length = 100)
    private String compAddrOther;

    @Size(max = 200)
    @Column(name = "comp_addr_en", length = 200)
    private String compAddrEn;

    @Size(max = 20)
    @Column(name = "apply_item", length = 20)
    private String applyItem;

    @Size(max = 3)
    @Column(name = "apply_type", length = 3)
    private String applyType;

    @Size(max = 20)
    @Column(name = "receive_no", length = 20)
    private String receiveNo;

    @Column(name = "public_date")
    private LocalDate publicDate;

    @Column(name = "effect_date_start")
    private LocalDate effectDateStart;

    @Column(name = "effect_date_end")
    private LocalDate effectDateEnd;

    @Size(max = 2000)
    @Column(name = "remark", length = 2000)
    private String remark;

    @Size(max = 3)
    @Column(name = "receive_type", length = 3)
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

    @Size(max = 200)
    @Column(name = "certificate_id_list", length = 200)
    private String certificateIdList;

    @Size(max = 100)
    @Column(name = "subject_list", length = 100)
    private String subjectList;

    @Column(name = "is_more_7_year")
    private String isMore7year;

    @Column(name = "is_xca_cert")
    private String isXcaCert;

    @Size(max = 100)
    @Column(name = "change_items", length = 100)
    private String changeItems;

    @Size(max = 400)
    @Column(name = "change_items_desc", length = 400)
    private String changeItemsDesc;

    @Size(max = 20)
    @Column(name = "contractor_id", length = 20)
    private String contractorId;

    @Size(max = 3)
    @Column(name = "status", length = 3)
    private String status;

    @Size(max = 20)
    @Column(name = "review_staff", length = 20)
    private String reviewStaff;

    @Column(name = "review_time")
    private Instant reviewTime;

    @Size(max = 2000)
    @Column(name = "review_desc", length = 2000)
    private String reviewDesc;

    @Size(max = 20)
    @Column(name = "create_user", length = 20)
    private String createUser;

    @Column(name = "create_time")
    private Instant createTime;

    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    @Column(name = "update_time")
    private Instant updateTime;

    @Size(max = 100)
    @Column(name = "transfer_from", length = 100)
    private String transferFrom;

    @Column(name = "transfer_time")
    private Instant transferTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getEngEngrLicenseId() {
        return this.engEngrLicenseId;
    }

    public EngEngrLicense engEngrLicenseId(String engEngrLicenseId) {
        this.setEngEngrLicenseId(engEngrLicenseId);
        return this;
    }

    public void setEngEngrLicenseId(String engEngrLicenseId) {
        this.engEngrLicenseId = engEngrLicenseId;
    }

    public BigDecimal getEngEngrLicenseVersion() {
        return this.engEngrLicenseVersion;
    }

    public EngEngrLicense engEngrLicenseVersion(BigDecimal engEngrLicenseVersion) {
        this.setEngEngrLicenseVersion(engEngrLicenseVersion);
        return this;
    }

    public void setEngEngrLicenseVersion(BigDecimal engEngrLicenseVersion) {
        this.engEngrLicenseVersion = engEngrLicenseVersion;
    }

    public String getEngEngrLicenseNo() {
        return this.engEngrLicenseNo;
    }

    public EngEngrLicense engEngrLicenseNo(String engEngrLicenseNo) {
        this.setEngEngrLicenseNo(engEngrLicenseNo);
        return this;
    }

    public void setEngEngrLicenseNo(String engEngrLicenseNo) {
        this.engEngrLicenseNo = engEngrLicenseNo;
    }

    public LocalDate getReciveDate() {
        return reciveDate;
    }

    public void setReciveDate(LocalDate reciveDate) {
        this.reciveDate = reciveDate;
    }

    public LocalDate getReceiveDate() {
        return this.receiveDate;
    }

    public EngEngrLicense receiveDate(LocalDate receiveDate) {
        this.setReceiveDate(receiveDate);
        return this;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getEngrLicenseDocNo() {
        return this.engrLicenseDocNo;
    }

    public EngEngrLicense engrLicenseDocNo(String engrLicenseDocNo) {
        this.setEngrLicenseDocNo(engrLicenseDocNo);
        return this;
    }

    public void setEngrLicenseDocNo(String engrLicenseDocNo) {
        this.engrLicenseDocNo = engrLicenseDocNo;
    }

    public String getEngrId() {
        return this.engrId;
    }

    public EngEngrLicense engrId(String engrId) {
        this.setEngrId(engrId);
        return this;
    }

    public void setEngrId(String engrId) {
        this.engrId = engrId;
    }

    public String getIdentityType() {
        return this.identityType;
    }

    public EngEngrLicense identityType(String identityType) {
        this.setIdentityType(identityType);
        return this;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdno() {
        return this.idno;
    }

    public EngEngrLicense idno(String idno) {
        this.setIdno(idno);
        return this;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getChName() {
        return this.chName;
    }

    public EngEngrLicense chName(String chName) {
        this.setChName(chName);
        return this;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getChNameRome() {
        return this.chNameRome;
    }

    public EngEngrLicense chNameRome(String chNameRome) {
        this.setChNameRome(chNameRome);
        return this;
    }

    public void setChNameRome(String chNameRome) {
        this.chNameRome = chNameRome;
    }

    public String getEnName() {
        return this.enName;
    }

    public EngEngrLicense enName(String enName) {
        this.setEnName(enName);
        return this;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public EngEngrLicense birthDate(LocalDate birthDate) {
        this.setBirthDate(birthDate);
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return this.sex;
    }

    public EngEngrLicense sex(String sex) {
        this.setSex(sex);
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobileTel() {
        return this.mobileTel;
    }

    public EngEngrLicense mobileTel(String mobileTel) {
        this.setMobileTel(mobileTel);
        return this;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getEmail() {
        return this.email;
    }

    public EngEngrLicense email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermanentAddrZip() {
        return this.permanentAddrZip;
    }

    public EngEngrLicense permanentAddrZip(String permanentAddrZip) {
        this.setPermanentAddrZip(permanentAddrZip);
        return this;
    }

    public void setPermanentAddrZip(String permanentAddrZip) {
        this.permanentAddrZip = permanentAddrZip;
    }

    public String getPermanentAddrCity() {
        return this.permanentAddrCity;
    }

    public EngEngrLicense permanentAddrCity(String permanentAddrCity) {
        this.setPermanentAddrCity(permanentAddrCity);
        return this;
    }

    public void setPermanentAddrCity(String permanentAddrCity) {
        this.permanentAddrCity = permanentAddrCity;
    }

    public String getPermanentAddrTown() {
        return this.permanentAddrTown;
    }

    public EngEngrLicense permanentAddrTown(String permanentAddrTown) {
        this.setPermanentAddrTown(permanentAddrTown);
        return this;
    }

    public void setPermanentAddrTown(String permanentAddrTown) {
        this.permanentAddrTown = permanentAddrTown;
    }

    public String getPermanentAddrOther() {
        return this.permanentAddrOther;
    }

    public EngEngrLicense permanentAddrOther(String permanentAddrOther) {
        this.setPermanentAddrOther(permanentAddrOther);
        return this;
    }

    public void setPermanentAddrOther(String permanentAddrOther) {
        this.permanentAddrOther = permanentAddrOther;
    }

    public String getContactAddrZip() {
        return this.contactAddrZip;
    }

    public EngEngrLicense contactAddrZip(String contactAddrZip) {
        this.setContactAddrZip(contactAddrZip);
        return this;
    }

    public void setContactAddrZip(String contactAddrZip) {
        this.contactAddrZip = contactAddrZip;
    }

    public String getContactAddrCity() {
        return this.contactAddrCity;
    }

    public EngEngrLicense contactAddrCity(String contactAddrCity) {
        this.setContactAddrCity(contactAddrCity);
        return this;
    }

    public void setContactAddrCity(String contactAddrCity) {
        this.contactAddrCity = contactAddrCity;
    }

    public String getContactAddrTown() {
        return this.contactAddrTown;
    }

    public EngEngrLicense contactAddrTown(String contactAddrTown) {
        this.setContactAddrTown(contactAddrTown);
        return this;
    }

    public void setContactAddrTown(String contactAddrTown) {
        this.contactAddrTown = contactAddrTown;
    }

    public String getContactAddrOther() {
        return this.contactAddrOther;
    }

    public EngEngrLicense contactAddrOther(String contactAddrOther) {
        this.setContactAddrOther(contactAddrOther);
        return this;
    }

    public void setContactAddrOther(String contactAddrOther) {
        this.contactAddrOther = contactAddrOther;
    }

    public String getExecType() {
        return this.execType;
    }

    public EngEngrLicense execType(String execType) {
        this.setExecType(execType);
        return this;
    }

    public void setExecType(String execType) {
        this.execType = execType;
    }

    public String getCompId() {
        return this.compId;
    }

    public EngEngrLicense compId(String compId) {
        this.setCompId(compId);
        return this;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getCompType() {
        return this.compType;
    }

    public EngEngrLicense compType(String compType) {
        this.setCompType(compType);
        return this;
    }

    public void setCompType(String compType) {
        this.compType = compType;
    }

    public String getCompIdno() {
        return this.compIdno;
    }

    public EngEngrLicense compIdno(String compIdno) {
        this.setCompIdno(compIdno);
        return this;
    }

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
    }

    public String getCompChName() {
        return this.compChName;
    }

    public EngEngrLicense compChName(String compChName) {
        this.setCompChName(compChName);
        return this;
    }

    public void setCompChName(String compChName) {
        this.compChName = compChName;
    }

    public String getCompEnName() {
        return this.compEnName;
    }

    public EngEngrLicense compEnName(String compEnName) {
        this.setCompEnName(compEnName);
        return this;
    }

    public void setCompEnName(String compEnName) {
        this.compEnName = compEnName;
    }

    public String getCompTel1() {
        return this.compTel1;
    }

    public EngEngrLicense compTel1(String compTel1) {
        this.setCompTel1(compTel1);
        return this;
    }

    public void setCompTel1(String compTel1) {
        this.compTel1 = compTel1;
    }

    public String getCompTel2() {
        return this.compTel2;
    }

    public EngEngrLicense compTel2(String compTel2) {
        this.setCompTel2(compTel2);
        return this;
    }

    public void setCompTel2(String compTel2) {
        this.compTel2 = compTel2;
    }

    public String getCompFax() {
        return this.compFax;
    }

    public EngEngrLicense compFax(String compFax) {
        this.setCompFax(compFax);
        return this;
    }

    public void setCompFax(String compFax) {
        this.compFax = compFax;
    }

    public String getCompAddrZip() {
        return this.compAddrZip;
    }

    public EngEngrLicense compAddrZip(String compAddrZip) {
        this.setCompAddrZip(compAddrZip);
        return this;
    }

    public void setCompAddrZip(String compAddrZip) {
        this.compAddrZip = compAddrZip;
    }

    public String getCompAddrCity() {
        return this.compAddrCity;
    }

    public EngEngrLicense compAddrCity(String compAddrCity) {
        this.setCompAddrCity(compAddrCity);
        return this;
    }

    public void setCompAddrCity(String compAddrCity) {
        this.compAddrCity = compAddrCity;
    }

    public String getCompAddrTown() {
        return this.compAddrTown;
    }

    public EngEngrLicense compAddrTown(String compAddrTown) {
        this.setCompAddrTown(compAddrTown);
        return this;
    }

    public void setCompAddrTown(String compAddrTown) {
        this.compAddrTown = compAddrTown;
    }

    public String getCompAddrOther() {
        return this.compAddrOther;
    }

    public EngEngrLicense compAddrOther(String compAddrOther) {
        this.setCompAddrOther(compAddrOther);
        return this;
    }

    public void setCompAddrOther(String compAddrOther) {
        this.compAddrOther = compAddrOther;
    }

    public String getCompAddrEn() {
        return this.compAddrEn;
    }

    public EngEngrLicense compAddrEn(String compAddrEn) {
        this.setCompAddrEn(compAddrEn);
        return this;
    }

    public void setCompAddrEn(String compAddrEn) {
        this.compAddrEn = compAddrEn;
    }

    public String getApplyItem() {
        return this.applyItem;
    }

    public EngEngrLicense applyItem(String applyItem) {
        this.setApplyItem(applyItem);
        return this;
    }

    public void setApplyItem(String applyItem) {
        this.applyItem = applyItem;
    }

    public String getApplyType() {
        return this.applyType;
    }

    public EngEngrLicense applyType(String applyType) {
        this.setApplyType(applyType);
        return this;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getReceiveNo() {
        return this.receiveNo;
    }

    public EngEngrLicense receiveNo(String receiveNo) {
        this.setReceiveNo(receiveNo);
        return this;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public LocalDate getPublicDate() {
        return this.publicDate;
    }

    public EngEngrLicense publicDate(LocalDate publicDate) {
        this.setPublicDate(publicDate);
        return this;
    }

    public void setPublicDate(LocalDate publicDate) {
        this.publicDate = publicDate;
    }

    public LocalDate getEffectDateStart() {
        return this.effectDateStart;
    }

    public EngEngrLicense effectDateStart(LocalDate effectDateStart) {
        this.setEffectDateStart(effectDateStart);
        return this;
    }

    public void setEffectDateStart(LocalDate effectDateStart) {
        this.effectDateStart = effectDateStart;
    }

    public LocalDate getEffectDateEnd() {
        return this.effectDateEnd;
    }

    public EngEngrLicense effectDateEnd(LocalDate effectDateEnd) {
        this.setEffectDateEnd(effectDateEnd);
        return this;
    }

    public void setEffectDateEnd(LocalDate effectDateEnd) {
        this.effectDateEnd = effectDateEnd;
    }

    public String getRemark() {
        return this.remark;
    }

    public EngEngrLicense remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReceiveType() {
        return this.receiveType;
    }

    public EngEngrLicense receiveType(String receiveType) {
        this.setReceiveType(receiveType);
        return this;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public String getReceiveAddrZip() {
        return this.receiveAddrZip;
    }

    public EngEngrLicense receiveAddrZip(String receiveAddrZip) {
        this.setReceiveAddrZip(receiveAddrZip);
        return this;
    }

    public void setReceiveAddrZip(String receiveAddrZip) {
        this.receiveAddrZip = receiveAddrZip;
    }

    public String getReceiveAddrCity() {
        return this.receiveAddrCity;
    }

    public EngEngrLicense receiveAddrCity(String receiveAddrCity) {
        this.setReceiveAddrCity(receiveAddrCity);
        return this;
    }

    public void setReceiveAddrCity(String receiveAddrCity) {
        this.receiveAddrCity = receiveAddrCity;
    }

    public String getReceiveAddrTown() {
        return this.receiveAddrTown;
    }

    public EngEngrLicense receiveAddrTown(String receiveAddrTown) {
        this.setReceiveAddrTown(receiveAddrTown);
        return this;
    }

    public void setReceiveAddrTown(String receiveAddrTown) {
        this.receiveAddrTown = receiveAddrTown;
    }

    public String getReceiveAddrOther() {
        return this.receiveAddrOther;
    }

    public EngEngrLicense receiveAddrOther(String receiveAddrOther) {
        this.setReceiveAddrOther(receiveAddrOther);
        return this;
    }

    public void setReceiveAddrOther(String receiveAddrOther) {
        this.receiveAddrOther = receiveAddrOther;
    }

    public String getCertificateIdList() {
        return this.certificateIdList;
    }

    public EngEngrLicense certificateIdList(String certificateIdList) {
        this.setCertificateIdList(certificateIdList);
        return this;
    }

    public void setCertificateIdList(String certificateIdList) {
        this.certificateIdList = certificateIdList;
    }

    public String getSubjectList() {
        return this.subjectList;
    }

    public EngEngrLicense subjectList(String subjectList) {
        this.setSubjectList(subjectList);
        return this;
    }

    public void setSubjectList(String subjectList) {
        this.subjectList = subjectList;
    }

    public String getIsMore7year() {
        return this.isMore7year;
    }

    public EngEngrLicense isMore7year(String isMore7year) {
        this.setIsMore7year(isMore7year);
        return this;
    }

    public void setIsMore7year(String isMore7year) {
        this.isMore7year = isMore7year;
    }

    public String getIsXcaCert() {
        return this.isXcaCert;
    }

    public EngEngrLicense isXcaCert(String isXcaCert) {
        this.setIsXcaCert(isXcaCert);
        return this;
    }

    public void setIsXcaCert(String isXcaCert) {
        this.isXcaCert = isXcaCert;
    }

    public String getChangeItems() {
        return this.changeItems;
    }

    public EngEngrLicense changeItems(String changeItems) {
        this.setChangeItems(changeItems);
        return this;
    }

    public void setChangeItems(String changeItems) {
        this.changeItems = changeItems;
    }

    public String getChangeItemsDesc() {
        return this.changeItemsDesc;
    }

    public EngEngrLicense changeItemsDesc(String changeItemsDesc) {
        this.setChangeItemsDesc(changeItemsDesc);
        return this;
    }

    public void setChangeItemsDesc(String changeItemsDesc) {
        this.changeItemsDesc = changeItemsDesc;
    }

    public String getContractorId() {
        return this.contractorId;
    }

    public EngEngrLicense contractorId(String contractorId) {
        this.setContractorId(contractorId);
        return this;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getStatus() {
        return this.status;
    }

    public EngEngrLicense status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReviewStaff() {
        return this.reviewStaff;
    }

    public EngEngrLicense reviewStaff(String reviewStaff) {
        this.setReviewStaff(reviewStaff);
        return this;
    }

    public void setReviewStaff(String reviewStaff) {
        this.reviewStaff = reviewStaff;
    }

    public Instant getReviewTime() {
        return this.reviewTime;
    }

    public EngEngrLicense reviewTime(Instant reviewTime) {
        this.setReviewTime(reviewTime);
        return this;
    }

    public void setReviewTime(Instant reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewDesc() {
        return this.reviewDesc;
    }

    public EngEngrLicense reviewDesc(String reviewDesc) {
        this.setReviewDesc(reviewDesc);
        return this;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EngEngrLicense createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EngEngrLicense createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EngEngrLicense updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EngEngrLicense updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public EngEngrLicense transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return this.transferTime;
    }

    public EngEngrLicense transferTime(Instant transferTime) {
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
        if (!(o instanceof EngEngrLicense)) {
            return false;
        }
        return engEngrLicenseId != null && engEngrLicenseId.equals(((EngEngrLicense) o).engEngrLicenseId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngEngrLicense{" +
            "engEngrLicenseId=" + getEngEngrLicenseId() +
            ", engEngrLicenseVersion=" + getEngEngrLicenseVersion() +
            ", engEngrLicenseNo='" + getEngEngrLicenseNo() + "'" +
            ", receiveDate='" + getReceiveDate() + "'" +
                ",reciveDate='" + getReciveDate() + "'" +
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
            "}";
    }
}
