package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A EngCstLicense.
 */
@Entity
@Table(name = "eng_cst_license")
public class EngCstLicense implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "eng_cst_license_id", length = 20, nullable = false)
    private String engCstLicenseId;

    @NotNull
    @Column(name = "eng_cst_license_version", precision = 21, scale = 2, nullable = false)
    private BigDecimal engCstLicenseVersion;

    @Size(max = 20)
    @Column(name = "eng_cst_license_no", length = 20)
    private String engCstLicenseNo;

    @Size(max = 20)
    @Column(name = "cst_license_doc_no", length = 20)
    private String cstLicenseDocNo;

    @Column(name = "receive_date")
    private LocalDate receiveDate;

    @Size(max = 20)
    @Column(name = "is_master_engr", length = 20)
    private String isMasterEngr;

    @Size(max = 20)
    @Column(name = "master_id", length = 20)
    private String masterId;

    @Size(max = 20)
    @Column(name = "master_name", length = 20)
    private String masterName;

    @Size(max = 20)
    @Column(name = "master_name_rome", length = 20)
    private String masterNameRome;

    @Size(max = 20)
    @Column(name = "master_name_eng", length = 20)
    private String masterNameEng;

    @Size(max = 20)
    @Column(name = "master_idno", length = 20)
    private String masterIdno;

    @Column(name = "master_birthdate")
    private LocalDate masterBirthdate;

    @Size(max = 20)
    @Column(name = "master_sex", length = 20)
    private String masterSex;

    @Size(max = 20)
    @Column(name = "master_mobile_tel", length = 20)
    private String masterMobileTel;

    @Size(max = 20)
    @Column(name = "master_permanent_addr_zip", length = 20)
    private String masterPermanentAddrZip;

    @Size(max = 20)
    @Column(name = "master_permanent_addr_city", length = 20)
    private String masterPermanentAddrCity;

    @Size(max = 20)
    @Column(name = "master_permanent_addr_town", length = 20)
    private String masterPermanentAddrTown;

    @Size(max = 20)
    @Column(name = "master_permanent_addr_other", length = 20)
    private String masterPermanentAddrOther;

    @Size(max = 20)
    @Column(name = "master_contact_addr_zip", length = 20)
    private String masterContactAddrZip;

    @Size(max = 20)
    @Column(name = "master_contact_addr_city", length = 20)
    private String masterContactAddrCity;

    @Size(max = 20)
    @Column(name = "master_contact_addr_town", length = 20)
    private String masterContactAddrTown;

    @Size(max = 20)
    @Column(name = "master_contact_addr_other", length = 20)
    private String masterContactAddrOther;

    @Size(max = 20)
    @Column(name = "master_email", length = 20)
    private String masterEmail;

    @Size(max = 20)
    @Column(name = "master_identity_type", length = 20)
    private String masterIdentityType;

    @Size(max = 20)
    @Column(name = "comp_id", length = 20)
    private String compId;

    @Size(max = 20)
    @Column(name = "comp_name", length = 20)
    private String compName;

    @Size(max = 20)
    @Column(name = "comp_idno", length = 20)
    private String compIdno;

    @Size(max = 20)
    @Column(name = "comp_tel_1", length = 20)
    private String compTel1;

    @Size(max = 20)
    @Column(name = "comp_tel_2", length = 20)
    private String compTel2;

    @Size(max = 20)
    @Column(name = "comp_fax", length = 20)
    private String compFax;

    @Size(max = 20)
    @Column(name = "comp_addr_zip", length = 20)
    private String compAddrZip;

    @Size(max = 20)
    @Column(name = "comp_addr_city", length = 20)
    private String compAddrCity;

    @Size(max = 20)
    @Column(name = "comp_addr_town", length = 20)
    private String compAddrTown;

    @Size(max = 20)
    @Column(name = "comp_addr_other", length = 20)
    private String compAddrOther;

    @Column(name = "capital", precision = 21, scale = 2)
    private BigDecimal capital;

    @Size(max = 20)
    @Column(name = "apply_item", length = 20)
    private String applyItem;

    @Size(max = 20)
    @Column(name = "apply_type", length = 20)
    private String applyType;

    @Size(max = 20)
    @Column(name = "receive_no", length = 20)
    private String receiveNo;

    @Size(max = 20)
    @Column(name = "contact_id", length = 20)
    private String contactId;

    @Column(name = "public_date")
    private LocalDate publicDate;

    @Size(max = 20)
    @Column(name = "area_master_org", length = 20)
    private String areaMasterOrg;

    @Size(max = 20)
    @Column(name = "sale_area_list", length = 20)
    private String saleAreaList;

    @Size(max = 20)
    @Column(name = "agree_law_1", length = 20)
    private String agreeLaw1;

    @Size(max = 20)
    @Column(name = "agree_law_2", length = 20)
    private String agreeLaw2;

    @Size(max = 20)
    @Column(name = "remark", length = 20)
    private String remark;

    @Size(max = 20)
    @Column(name = "receive_type", length = 20)
    private String receiveType;

    @Size(max = 20)
    @Column(name = "receive_addr_zip", length = 20)
    private String receiveAddrZip;

    @Size(max = 20)
    @Column(name = "receive_addr_city", length = 20)
    private String receiveAddrCity;

    @Size(max = 20)
    @Column(name = "receive_addr_town", length = 20)
    private String receiveAddrTown;

    @Size(max = 20)
    @Column(name = "receive_addr_other", length = 20)
    private String receiveAddrOther;

    @Size(max = 20)
    @Column(name = "status", length = 20)
    private String status;

    @Size(max = 20)
    @Column(name = "review_staff", length = 20)
    private String reviewStaff;

    @Column(name = "review_time")
    private Instant reviewTime;

    @Size(max = 20)
    @Column(name = "review_desc", length = 20)
    private String reviewDesc;

    @Size(max = 20)
    @Column(name = "change_items", length = 20)
    private String changeItems;

    @Size(max = 20)
    @Column(name = "change_items_desc", length = 20)
    private String changeItemsDesc;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    @Column(name = "transfer_date")
    private Instant transferDate;

    @Size(max = 20)
    @Column(name = "transfer_from", length = 20)
    private String transferFrom;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getEngCstLicenseId() {
        return this.engCstLicenseId;
    }

    public EngCstLicense engCstLicenseId(String engCstLicenseId) {
        this.setEngCstLicenseId(engCstLicenseId);
        return this;
    }

    public void setEngCstLicenseId(String engCstLicenseId) {
        this.engCstLicenseId = engCstLicenseId;
    }

    public BigDecimal getEngCstLicenseVersion() {
        return this.engCstLicenseVersion;
    }

    public EngCstLicense engCstLicenseVersion(BigDecimal engCstLicenseVersion) {
        this.setEngCstLicenseVersion(engCstLicenseVersion);
        return this;
    }

    public void setEngCstLicenseVersion(BigDecimal engCstLicenseVersion) {
        this.engCstLicenseVersion = engCstLicenseVersion;
    }

    public String getEngCstLicenseNo() {
        return this.engCstLicenseNo;
    }

    public EngCstLicense engCstLicenseNo(String engCstLicenseNo) {
        this.setEngCstLicenseNo(engCstLicenseNo);
        return this;
    }

    public void setEngCstLicenseNo(String engCstLicenseNo) {
        this.engCstLicenseNo = engCstLicenseNo;
    }

    public String getCstLicenseDocNo() {
        return this.cstLicenseDocNo;
    }

    public EngCstLicense cstLicenseDocNo(String cstLicenseDocNo) {
        this.setCstLicenseDocNo(cstLicenseDocNo);
        return this;
    }

    public void setCstLicenseDocNo(String cstLicenseDocNo) {
        this.cstLicenseDocNo = cstLicenseDocNo;
    }

    public LocalDate getReceiveDate() {
        return this.receiveDate;
    }

    public EngCstLicense receiveDate(LocalDate receiveDate) {
        this.setReceiveDate(receiveDate);
        return this;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getIsMasterEngr() {
        return this.isMasterEngr;
    }

    public EngCstLicense isMasterEngr(String isMasterEngr) {
        this.setIsMasterEngr(isMasterEngr);
        return this;
    }

    public void setIsMasterEngr(String isMasterEngr) {
        this.isMasterEngr = isMasterEngr;
    }

    public String getMasterId() {
        return this.masterId;
    }

    public EngCstLicense masterId(String masterId) {
        this.setMasterId(masterId);
        return this;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getMasterName() {
        return this.masterName;
    }

    public EngCstLicense masterName(String masterName) {
        this.setMasterName(masterName);
        return this;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getMasterNameRome() {
        return this.masterNameRome;
    }

    public EngCstLicense masterNameRome(String masterNameRome) {
        this.setMasterNameRome(masterNameRome);
        return this;
    }

    public void setMasterNameRome(String masterNameRome) {
        this.masterNameRome = masterNameRome;
    }

    public String getMasterNameEng() {
        return this.masterNameEng;
    }

    public EngCstLicense masterNameEng(String masterNameEng) {
        this.setMasterNameEng(masterNameEng);
        return this;
    }

    public void setMasterNameEng(String masterNameEng) {
        this.masterNameEng = masterNameEng;
    }

    public String getMasterIdno() {
        return this.masterIdno;
    }

    public EngCstLicense masterIdno(String masterIdno) {
        this.setMasterIdno(masterIdno);
        return this;
    }

    public void setMasterIdno(String masterIdno) {
        this.masterIdno = masterIdno;
    }

    public LocalDate getMasterBirthdate() {
        return this.masterBirthdate;
    }

    public EngCstLicense masterBirthdate(LocalDate masterBirthdate) {
        this.setMasterBirthdate(masterBirthdate);
        return this;
    }

    public void setMasterBirthdate(LocalDate masterBirthdate) {
        this.masterBirthdate = masterBirthdate;
    }

    public String getMasterSex() {
        return this.masterSex;
    }

    public EngCstLicense masterSex(String masterSex) {
        this.setMasterSex(masterSex);
        return this;
    }

    public void setMasterSex(String masterSex) {
        this.masterSex = masterSex;
    }

    public String getMasterMobileTel() {
        return this.masterMobileTel;
    }

    public EngCstLicense masterMobileTel(String masterMobileTel) {
        this.setMasterMobileTel(masterMobileTel);
        return this;
    }

    public void setMasterMobileTel(String masterMobileTel) {
        this.masterMobileTel = masterMobileTel;
    }

    public String getMasterPermanentAddrZip() {
        return this.masterPermanentAddrZip;
    }

    public EngCstLicense masterPermanentAddrZip(String masterPermanentAddrZip) {
        this.setMasterPermanentAddrZip(masterPermanentAddrZip);
        return this;
    }

    public void setMasterPermanentAddrZip(String masterPermanentAddrZip) {
        this.masterPermanentAddrZip = masterPermanentAddrZip;
    }

    public String getMasterPermanentAddrCity() {
        return this.masterPermanentAddrCity;
    }

    public EngCstLicense masterPermanentAddrCity(String masterPermanentAddrCity) {
        this.setMasterPermanentAddrCity(masterPermanentAddrCity);
        return this;
    }

    public void setMasterPermanentAddrCity(String masterPermanentAddrCity) {
        this.masterPermanentAddrCity = masterPermanentAddrCity;
    }

    public String getMasterPermanentAddrTown() {
        return this.masterPermanentAddrTown;
    }

    public EngCstLicense masterPermanentAddrTown(String masterPermanentAddrTown) {
        this.setMasterPermanentAddrTown(masterPermanentAddrTown);
        return this;
    }

    public void setMasterPermanentAddrTown(String masterPermanentAddrTown) {
        this.masterPermanentAddrTown = masterPermanentAddrTown;
    }

    public String getMasterPermanentAddrOther() {
        return this.masterPermanentAddrOther;
    }

    public EngCstLicense masterPermanentAddrOther(String masterPermanentAddrOther) {
        this.setMasterPermanentAddrOther(masterPermanentAddrOther);
        return this;
    }

    public void setMasterPermanentAddrOther(String masterPermanentAddrOther) {
        this.masterPermanentAddrOther = masterPermanentAddrOther;
    }

    public String getMasterContactAddrZip() {
        return this.masterContactAddrZip;
    }

    public EngCstLicense masterContactAddrZip(String masterContactAddrZip) {
        this.setMasterContactAddrZip(masterContactAddrZip);
        return this;
    }

    public void setMasterContactAddrZip(String masterContactAddrZip) {
        this.masterContactAddrZip = masterContactAddrZip;
    }

    public String getMasterContactAddrCity() {
        return this.masterContactAddrCity;
    }

    public EngCstLicense masterContactAddrCity(String masterContactAddrCity) {
        this.setMasterContactAddrCity(masterContactAddrCity);
        return this;
    }

    public void setMasterContactAddrCity(String masterContactAddrCity) {
        this.masterContactAddrCity = masterContactAddrCity;
    }

    public String getMasterContactAddrTown() {
        return this.masterContactAddrTown;
    }

    public EngCstLicense masterContactAddrTown(String masterContactAddrTown) {
        this.setMasterContactAddrTown(masterContactAddrTown);
        return this;
    }

    public void setMasterContactAddrTown(String masterContactAddrTown) {
        this.masterContactAddrTown = masterContactAddrTown;
    }

    public String getMasterContactAddrOther() {
        return this.masterContactAddrOther;
    }

    public EngCstLicense masterContactAddrOther(String masterContactAddrOther) {
        this.setMasterContactAddrOther(masterContactAddrOther);
        return this;
    }

    public void setMasterContactAddrOther(String masterContactAddrOther) {
        this.masterContactAddrOther = masterContactAddrOther;
    }

    public String getMasterEmail() {
        return this.masterEmail;
    }

    public EngCstLicense masterEmail(String masterEmail) {
        this.setMasterEmail(masterEmail);
        return this;
    }

    public void setMasterEmail(String masterEmail) {
        this.masterEmail = masterEmail;
    }

    public String getMasterIdentityType() {
        return this.masterIdentityType;
    }

    public EngCstLicense masterIdentityType(String masterIdentityType) {
        this.setMasterIdentityType(masterIdentityType);
        return this;
    }

    public void setMasterIdentityType(String masterIdentityType) {
        this.masterIdentityType = masterIdentityType;
    }

    public String getCompId() {
        return this.compId;
    }

    public EngCstLicense compId(String compId) {
        this.setCompId(compId);
        return this;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getCompName() {
        return this.compName;
    }

    public EngCstLicense compName(String compName) {
        this.setCompName(compName);
        return this;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompIdno() {
        return this.compIdno;
    }

    public EngCstLicense compIdno(String compIdno) {
        this.setCompIdno(compIdno);
        return this;
    }

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
    }

    public String getCompTel1() {
        return this.compTel1;
    }

    public EngCstLicense compTel1(String compTel1) {
        this.setCompTel1(compTel1);
        return this;
    }

    public void setCompTel1(String compTel1) {
        this.compTel1 = compTel1;
    }

    public String getCompTel2() {
        return this.compTel2;
    }

    public EngCstLicense compTel2(String compTel2) {
        this.setCompTel2(compTel2);
        return this;
    }

    public void setCompTel2(String compTel2) {
        this.compTel2 = compTel2;
    }

    public String getCompFax() {
        return this.compFax;
    }

    public EngCstLicense compFax(String compFax) {
        this.setCompFax(compFax);
        return this;
    }

    public void setCompFax(String compFax) {
        this.compFax = compFax;
    }

    public String getCompAddrZip() {
        return this.compAddrZip;
    }

    public EngCstLicense compAddrZip(String compAddrZip) {
        this.setCompAddrZip(compAddrZip);
        return this;
    }

    public void setCompAddrZip(String compAddrZip) {
        this.compAddrZip = compAddrZip;
    }

    public String getCompAddrCity() {
        return this.compAddrCity;
    }

    public EngCstLicense compAddrCity(String compAddrCity) {
        this.setCompAddrCity(compAddrCity);
        return this;
    }

    public void setCompAddrCity(String compAddrCity) {
        this.compAddrCity = compAddrCity;
    }

    public String getCompAddrTown() {
        return this.compAddrTown;
    }

    public EngCstLicense compAddrTown(String compAddrTown) {
        this.setCompAddrTown(compAddrTown);
        return this;
    }

    public void setCompAddrTown(String compAddrTown) {
        this.compAddrTown = compAddrTown;
    }

    public String getCompAddrOther() {
        return this.compAddrOther;
    }

    public EngCstLicense compAddrOther(String compAddrOther) {
        this.setCompAddrOther(compAddrOther);
        return this;
    }

    public void setCompAddrOther(String compAddrOther) {
        this.compAddrOther = compAddrOther;
    }

    public BigDecimal getCapital() {
        return this.capital;
    }

    public EngCstLicense capital(BigDecimal capital) {
        this.setCapital(capital);
        return this;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public String getApplyItem() {
        return this.applyItem;
    }

    public EngCstLicense applyItem(String applyItem) {
        this.setApplyItem(applyItem);
        return this;
    }

    public void setApplyItem(String applyItem) {
        this.applyItem = applyItem;
    }

    public String getApplyType() {
        return this.applyType;
    }

    public EngCstLicense applyType(String applyType) {
        this.setApplyType(applyType);
        return this;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getReceiveNo() {
        return this.receiveNo;
    }

    public EngCstLicense receiveNo(String receiveNo) {
        this.setReceiveNo(receiveNo);
        return this;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getContactId() {
        return this.contactId;
    }

    public EngCstLicense contactId(String contactId) {
        this.setContactId(contactId);
        return this;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public LocalDate getPublicDate() {
        return this.publicDate;
    }

    public EngCstLicense publicDate(LocalDate publicDate) {
        this.setPublicDate(publicDate);
        return this;
    }

    public void setPublicDate(LocalDate publicDate) {
        this.publicDate = publicDate;
    }

    public String getAreaMasterOrg() {
        return this.areaMasterOrg;
    }

    public EngCstLicense areaMasterOrg(String areaMasterOrg) {
        this.setAreaMasterOrg(areaMasterOrg);
        return this;
    }

    public void setAreaMasterOrg(String areaMasterOrg) {
        this.areaMasterOrg = areaMasterOrg;
    }

    public String getSaleAreaList() {
        return this.saleAreaList;
    }

    public EngCstLicense saleAreaList(String saleAreaList) {
        this.setSaleAreaList(saleAreaList);
        return this;
    }

    public void setSaleAreaList(String saleAreaList) {
        this.saleAreaList = saleAreaList;
    }

    public String getAgreeLaw1() {
        return this.agreeLaw1;
    }

    public EngCstLicense agreeLaw1(String agreeLaw1) {
        this.setAgreeLaw1(agreeLaw1);
        return this;
    }

    public void setAgreeLaw1(String agreeLaw1) {
        this.agreeLaw1 = agreeLaw1;
    }

    public String getAgreeLaw2() {
        return this.agreeLaw2;
    }

    public EngCstLicense agreeLaw2(String agreeLaw2) {
        this.setAgreeLaw2(agreeLaw2);
        return this;
    }

    public void setAgreeLaw2(String agreeLaw2) {
        this.agreeLaw2 = agreeLaw2;
    }

    public String getRemark() {
        return this.remark;
    }

    public EngCstLicense remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReceiveType() {
        return this.receiveType;
    }

    public EngCstLicense receiveType(String receiveType) {
        this.setReceiveType(receiveType);
        return this;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public String getReceiveAddrZip() {
        return this.receiveAddrZip;
    }

    public EngCstLicense receiveAddrZip(String receiveAddrZip) {
        this.setReceiveAddrZip(receiveAddrZip);
        return this;
    }

    public void setReceiveAddrZip(String receiveAddrZip) {
        this.receiveAddrZip = receiveAddrZip;
    }

    public String getReceiveAddrCity() {
        return this.receiveAddrCity;
    }

    public EngCstLicense receiveAddrCity(String receiveAddrCity) {
        this.setReceiveAddrCity(receiveAddrCity);
        return this;
    }

    public void setReceiveAddrCity(String receiveAddrCity) {
        this.receiveAddrCity = receiveAddrCity;
    }

    public String getReceiveAddrTown() {
        return this.receiveAddrTown;
    }

    public EngCstLicense receiveAddrTown(String receiveAddrTown) {
        this.setReceiveAddrTown(receiveAddrTown);
        return this;
    }

    public void setReceiveAddrTown(String receiveAddrTown) {
        this.receiveAddrTown = receiveAddrTown;
    }

    public String getReceiveAddrOther() {
        return this.receiveAddrOther;
    }

    public EngCstLicense receiveAddrOther(String receiveAddrOther) {
        this.setReceiveAddrOther(receiveAddrOther);
        return this;
    }

    public void setReceiveAddrOther(String receiveAddrOther) {
        this.receiveAddrOther = receiveAddrOther;
    }

    public String getStatus() {
        return this.status;
    }

    public EngCstLicense status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReviewStaff() {
        return this.reviewStaff;
    }

    public EngCstLicense reviewStaff(String reviewStaff) {
        this.setReviewStaff(reviewStaff);
        return this;
    }

    public void setReviewStaff(String reviewStaff) {
        this.reviewStaff = reviewStaff;
    }

    public Instant getReviewTime() {
        return this.reviewTime;
    }

    public EngCstLicense reviewTime(Instant reviewTime) {
        this.setReviewTime(reviewTime);
        return this;
    }

    public void setReviewTime(Instant reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewDesc() {
        return this.reviewDesc;
    }

    public EngCstLicense reviewDesc(String reviewDesc) {
        this.setReviewDesc(reviewDesc);
        return this;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc;
    }

    public String getChangeItems() {
        return this.changeItems;
    }

    public EngCstLicense changeItems(String changeItems) {
        this.setChangeItems(changeItems);
        return this;
    }

    public void setChangeItems(String changeItems) {
        this.changeItems = changeItems;
    }

    public String getChangeItemsDesc() {
        return this.changeItemsDesc;
    }

    public EngCstLicense changeItemsDesc(String changeItemsDesc) {
        this.setChangeItemsDesc(changeItemsDesc);
        return this;
    }

    public void setChangeItemsDesc(String changeItemsDesc) {
        this.changeItemsDesc = changeItemsDesc;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EngCstLicense createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EngCstLicense createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EngCstLicense updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EngCstLicense updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Instant getTransferDate() {
        return this.transferDate;
    }

    public EngCstLicense transferDate(Instant transferDate) {
        this.setTransferDate(transferDate);
        return this;
    }

    public void setTransferDate(Instant transferDate) {
        this.transferDate = transferDate;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public EngCstLicense transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngCstLicense)) {
            return false;
        }
        return engCstLicenseId != null && engCstLicenseId.equals(((EngCstLicense) o).engCstLicenseId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngCstLicense{" +
            "engCstLicenseId=" + getEngCstLicenseId() +
            ", engCstLicenseVersion=" + getEngCstLicenseVersion() +
            ", engCstLicenseNo='" + getEngCstLicenseNo() + "'" +
            ", cstLicenseDocNo='" + getCstLicenseDocNo() + "'" +
            ", receiveDate='" + getReceiveDate() + "'" +
            ", isMasterEngr='" + getIsMasterEngr() + "'" +
            ", masterId='" + getMasterId() + "'" +
            ", masterName='" + getMasterName() + "'" +
            ", masterNameRome='" + getMasterNameRome() + "'" +
            ", masterNameEng='" + getMasterNameEng() + "'" +
            ", masterIdno='" + getMasterIdno() + "'" +
            ", masterBirthdate='" + getMasterBirthdate() + "'" +
            ", masterSex='" + getMasterSex() + "'" +
            ", masterMobileTel='" + getMasterMobileTel() + "'" +
            ", masterPermanentAddrZip='" + getMasterPermanentAddrZip() + "'" +
            ", masterPermanentAddrCity='" + getMasterPermanentAddrCity() + "'" +
            ", masterPermanentAddrTown='" + getMasterPermanentAddrTown() + "'" +
            ", masterPermanentAddrOther='" + getMasterPermanentAddrOther() + "'" +
            ", masterContactAddrZip='" + getMasterContactAddrZip() + "'" +
            ", masterContactAddrCity='" + getMasterContactAddrCity() + "'" +
            ", masterContactAddrTown='" + getMasterContactAddrTown() + "'" +
            ", masterContactAddrOther='" + getMasterContactAddrOther() + "'" +
            ", masterEmail='" + getMasterEmail() + "'" +
            ", masterIdentityType='" + getMasterIdentityType() + "'" +
            ", compId='" + getCompId() + "'" +
            ", compName='" + getCompName() + "'" +
            ", compIdno='" + getCompIdno() + "'" +
            ", compTel1='" + getCompTel1() + "'" +
            ", compTel2='" + getCompTel2() + "'" +
            ", compFax='" + getCompFax() + "'" +
            ", compAddrZip='" + getCompAddrZip() + "'" +
            ", compAddrCity='" + getCompAddrCity() + "'" +
            ", compAddrTown='" + getCompAddrTown() + "'" +
            ", compAddrOther='" + getCompAddrOther() + "'" +
            ", capital=" + getCapital() +
            ", applyItem='" + getApplyItem() + "'" +
            ", applyType='" + getApplyType() + "'" +
            ", receiveNo='" + getReceiveNo() + "'" +
            ", contactId='" + getContactId() + "'" +
            ", publicDate='" + getPublicDate() + "'" +
            ", areaMasterOrg='" + getAreaMasterOrg() + "'" +
            ", saleAreaList='" + getSaleAreaList() + "'" +
            ", agreeLaw1='" + getAgreeLaw1() + "'" +
            ", agreeLaw2='" + getAgreeLaw2() + "'" +
            ", remark='" + getRemark() + "'" +
            ", receiveType='" + getReceiveType() + "'" +
            ", receiveAddrZip='" + getReceiveAddrZip() + "'" +
            ", receiveAddrCity='" + getReceiveAddrCity() + "'" +
            ", receiveAddrTown='" + getReceiveAddrTown() + "'" +
            ", receiveAddrOther='" + getReceiveAddrOther() + "'" +
            ", status='" + getStatus() + "'" +
            ", reviewStaff='" + getReviewStaff() + "'" +
            ", reviewTime='" + getReviewTime() + "'" +
            ", reviewDesc='" + getReviewDesc() + "'" +
            ", changeItems='" + getChangeItems() + "'" +
            ", changeItemsDesc='" + getChangeItemsDesc() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", transferDate='" + getTransferDate() + "'" +
            ", transferFrom='" + getTransferFrom() + "'" +
            "}";
    }
}
