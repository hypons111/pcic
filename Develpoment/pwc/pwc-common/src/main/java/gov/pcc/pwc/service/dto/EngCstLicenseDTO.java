package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EngCstLicense} entity.
 */
public class EngCstLicenseDTO implements Serializable {

    @NotNull
    @Size(max = 20)
    private String engCstLicenseId;

    @NotNull
    private BigDecimal engCstLicenseVersion;

    @Size(max = 20)
    private String engCstLicenseNo;

    @Size(max = 20)
    private String cstLicenseDocNo;

    private LocalDate receiveDate;

    @Size(max = 20)
    private String isMasterEngr;

    @Size(max = 20)
    private String masterId;

    @Size(max = 20)
    private String masterName;

    @Size(max = 20)
    private String masterNameRome;

    @Size(max = 20)
    private String masterNameEng;

    @Size(max = 20)
    private String masterIdno;

    private LocalDate masterBirthdate;

    @Size(max = 20)
    private String masterSex;

    @Size(max = 20)
    private String masterMobileTel;

    @Size(max = 20)
    private String masterPermanentAddrZip;

    @Size(max = 20)
    private String masterPermanentAddrCity;

    @Size(max = 20)
    private String masterPermanentAddrTown;

    @Size(max = 20)
    private String masterPermanentAddrOther;

    @Size(max = 20)
    private String masterContactAddrZip;

    @Size(max = 20)
    private String masterContactAddrCity;

    @Size(max = 20)
    private String masterContactAddrTown;

    @Size(max = 20)
    private String masterContactAddrOther;

    @Size(max = 20)
    private String masterEmail;

    @Size(max = 20)
    private String masterIdentityType;

    @Size(max = 20)
    private String compId;

    @Size(max = 20)
    private String compName;

    @Size(max = 20)
    private String compIdno;

    @Size(max = 20)
    private String compTel1;

    @Size(max = 20)
    private String compTel2;

    @Size(max = 20)
    private String compFax;

    @Size(max = 20)
    private String compAddrZip;

    @Size(max = 20)
    private String compAddrCity;

    @Size(max = 20)
    private String compAddrTown;

    @Size(max = 20)
    private String compAddrOther;

    private BigDecimal capital;

    @Size(max = 20)
    private String applyItem;

    @Size(max = 20)
    private String applyType;

    @Size(max = 20)
    private String receiveNo;

    @Size(max = 20)
    private String contactId;

    private LocalDate publicDate;

    @Size(max = 20)
    private String areaMasterOrg;

    @Size(max = 20)
    private String saleAreaList;

    @Size(max = 20)
    private String agreeLaw1;

    @Size(max = 20)
    private String agreeLaw2;

    @Size(max = 20)
    private String remark;

    @Size(max = 20)
    private String receiveType;

    @Size(max = 20)
    private String receiveAddrZip;

    @Size(max = 20)
    private String receiveAddrCity;

    @Size(max = 20)
    private String receiveAddrTown;

    @Size(max = 20)
    private String receiveAddrOther;

    @Size(max = 20)
    private String status;

    @Size(max = 20)
    private String reviewStaff;

    private Instant reviewTime;

    @Size(max = 20)
    private String reviewDesc;

    @Size(max = 20)
    private String changeItems;

    @Size(max = 20)
    private String changeItemsDesc;

    @NotNull
    @Size(max = 20)
    private String createUser;

    @NotNull
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    private String updateUser;

    @NotNull
    private Instant updateTime;

    private Instant transferDate;

    @Size(max = 20)
    private String transferFrom;

    public String getEngCstLicenseId() {
        return engCstLicenseId;
    }

    public void setEngCstLicenseId(String engCstLicenseId) {
        this.engCstLicenseId = engCstLicenseId;
    }

    public BigDecimal getEngCstLicenseVersion() {
        return engCstLicenseVersion;
    }

    public void setEngCstLicenseVersion(BigDecimal engCstLicenseVersion) {
        this.engCstLicenseVersion = engCstLicenseVersion;
    }

    public String getEngCstLicenseNo() {
        return engCstLicenseNo;
    }

    public void setEngCstLicenseNo(String engCstLicenseNo) {
        this.engCstLicenseNo = engCstLicenseNo;
    }

    public String getCstLicenseDocNo() {
        return cstLicenseDocNo;
    }

    public void setCstLicenseDocNo(String cstLicenseDocNo) {
        this.cstLicenseDocNo = cstLicenseDocNo;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getIsMasterEngr() {
        return isMasterEngr;
    }

    public void setIsMasterEngr(String isMasterEngr) {
        this.isMasterEngr = isMasterEngr;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getMasterNameRome() {
        return masterNameRome;
    }

    public void setMasterNameRome(String masterNameRome) {
        this.masterNameRome = masterNameRome;
    }

    public String getMasterNameEng() {
        return masterNameEng;
    }

    public void setMasterNameEng(String masterNameEng) {
        this.masterNameEng = masterNameEng;
    }

    public String getMasterIdno() {
        return masterIdno;
    }

    public void setMasterIdno(String masterIdno) {
        this.masterIdno = masterIdno;
    }

    public LocalDate getMasterBirthdate() {
        return masterBirthdate;
    }

    public void setMasterBirthdate(LocalDate masterBirthdate) {
        this.masterBirthdate = masterBirthdate;
    }

    public String getMasterSex() {
        return masterSex;
    }

    public void setMasterSex(String masterSex) {
        this.masterSex = masterSex;
    }

    public String getMasterMobileTel() {
        return masterMobileTel;
    }

    public void setMasterMobileTel(String masterMobileTel) {
        this.masterMobileTel = masterMobileTel;
    }

    public String getMasterPermanentAddrZip() {
        return masterPermanentAddrZip;
    }

    public void setMasterPermanentAddrZip(String masterPermanentAddrZip) {
        this.masterPermanentAddrZip = masterPermanentAddrZip;
    }

    public String getMasterPermanentAddrCity() {
        return masterPermanentAddrCity;
    }

    public void setMasterPermanentAddrCity(String masterPermanentAddrCity) {
        this.masterPermanentAddrCity = masterPermanentAddrCity;
    }

    public String getMasterPermanentAddrTown() {
        return masterPermanentAddrTown;
    }

    public void setMasterPermanentAddrTown(String masterPermanentAddrTown) {
        this.masterPermanentAddrTown = masterPermanentAddrTown;
    }

    public String getMasterPermanentAddrOther() {
        return masterPermanentAddrOther;
    }

    public void setMasterPermanentAddrOther(String masterPermanentAddrOther) {
        this.masterPermanentAddrOther = masterPermanentAddrOther;
    }

    public String getMasterContactAddrZip() {
        return masterContactAddrZip;
    }

    public void setMasterContactAddrZip(String masterContactAddrZip) {
        this.masterContactAddrZip = masterContactAddrZip;
    }

    public String getMasterContactAddrCity() {
        return masterContactAddrCity;
    }

    public void setMasterContactAddrCity(String masterContactAddrCity) {
        this.masterContactAddrCity = masterContactAddrCity;
    }

    public String getMasterContactAddrTown() {
        return masterContactAddrTown;
    }

    public void setMasterContactAddrTown(String masterContactAddrTown) {
        this.masterContactAddrTown = masterContactAddrTown;
    }

    public String getMasterContactAddrOther() {
        return masterContactAddrOther;
    }

    public void setMasterContactAddrOther(String masterContactAddrOther) {
        this.masterContactAddrOther = masterContactAddrOther;
    }

    public String getMasterEmail() {
        return masterEmail;
    }

    public void setMasterEmail(String masterEmail) {
        this.masterEmail = masterEmail;
    }

    public String getMasterIdentityType() {
        return masterIdentityType;
    }

    public void setMasterIdentityType(String masterIdentityType) {
        this.masterIdentityType = masterIdentityType;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompIdno() {
        return compIdno;
    }

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
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

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
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

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public LocalDate getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(LocalDate publicDate) {
        this.publicDate = publicDate;
    }

    public String getAreaMasterOrg() {
        return areaMasterOrg;
    }

    public void setAreaMasterOrg(String areaMasterOrg) {
        this.areaMasterOrg = areaMasterOrg;
    }

    public String getSaleAreaList() {
        return saleAreaList;
    }

    public void setSaleAreaList(String saleAreaList) {
        this.saleAreaList = saleAreaList;
    }

    public String getAgreeLaw1() {
        return agreeLaw1;
    }

    public void setAgreeLaw1(String agreeLaw1) {
        this.agreeLaw1 = agreeLaw1;
    }

    public String getAgreeLaw2() {
        return agreeLaw2;
    }

    public void setAgreeLaw2(String agreeLaw2) {
        this.agreeLaw2 = agreeLaw2;
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

    public Instant getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Instant transferDate) {
        this.transferDate = transferDate;
    }

    public String getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngCstLicenseDTO)) {
            return false;
        }

        EngCstLicenseDTO engCstLicenseDTO = (EngCstLicenseDTO) o;
        if (this.engCstLicenseId == null) {
            return false;
        }
        return Objects.equals(this.engCstLicenseId, engCstLicenseDTO.engCstLicenseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.engCstLicenseId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngCstLicenseDTO{" +
            "engCstLicenseId='" + getEngCstLicenseId() + "'" +
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
