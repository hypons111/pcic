package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.MrpProjectPurchase} entity.
 */
public class MrpProjectPurchaseDTO implements Serializable {

    @NotNull
    @Size(max = 50)
    private String contractCode;

    @Size(max = 100)
    private String tenderAwardType;

    @NotNull
    @Size(max = 100)
    private String tenderOrgName;

    @NotNull
    @Size(max = 30)
    private String tenderOrgId;

    @NotNull
    @Size(max = 50)
    private String tenderCaseNo;

    @NotNull
    @Size(max = 200)
    private String tenderName;

    @Size(max = 100)
    private String tenderCpcCode;

    private Double tenderBudget;

    @Size(max = 100)
    private String tenderProcurementRange;

    @Size(max = 100)
    private String tenderWay;

    @Size(max = 100)
    private String awardWay;

    private Double tenderGovEstimate;

    private Double awardPrice;

    @Size(max = 100)
    private String awardPricePublic;

    @Size(max = 20)
    private String bidderId;

    @Size(max = 100)
    private String bidderSuppName;

    @Size(max = 100)
    private String tenderLimitedLaw;

    private Boolean isPackage;

    private Boolean isMultipleAward;

    private Boolean isInterEntitySupply;

    private Instant awardNoticeDate;

    private Instant awardDate;

    private Integer supplierAmount;

    @NotNull
    @Size(max = 100)
    private String tenderStatus;

    @Size(max = 100)
    private String tenderContactPerson;

    @Size(max = 100)
    private String tenderContactTel;

    @NotNull
    @Size(max = 100)
    private String tenderSeq;

    @Size(max = 100)
    private String bidderAwardAmount;

    @Size(max = 100)
    private String tenderIsLowestLaw;

    @Size(max = 100)
    private String tenderIsChgContract;

    @Size(max = 100)
    private String budgetPublic;

    @Size(max = 100)
    private String tenderGovestPublic;

    @Size(max = 50)
    private String oid;

    @Size(max = 50)
    private String awardUpdateSeq;

    private Instant dateOfOpeningOfTenders;

    private Instant dateOfOriginalPublication;

    @NotNull
    @Size(max = 10)
    private String awardSeq;

    private Integer bidderAmount;

    @NotNull
    private Integer kind;

    @Size(max = 50)
    private String pkPmsMain;

    @Size(max = 100)
    private String tenderEmail;

    @Size(max = 1000)
    private String tenderLocationArea;

    private Integer awardItemAmount;

    @NotNull
    private Long sn;

    @Size(max = 20)
    private String updateId;

    private Instant updateDate;

    @NotNull
    @Size(max = 20)
    private String createId;

    private Instant createDate;

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getTenderAwardType() {
        return tenderAwardType;
    }

    public void setTenderAwardType(String tenderAwardType) {
        this.tenderAwardType = tenderAwardType;
    }

    public String getTenderOrgName() {
        return tenderOrgName;
    }

    public void setTenderOrgName(String tenderOrgName) {
        this.tenderOrgName = tenderOrgName;
    }

    public String getTenderOrgId() {
        return tenderOrgId;
    }

    public void setTenderOrgId(String tenderOrgId) {
        this.tenderOrgId = tenderOrgId;
    }

    public String getTenderCaseNo() {
        return tenderCaseNo;
    }

    public void setTenderCaseNo(String tenderCaseNo) {
        this.tenderCaseNo = tenderCaseNo;
    }

    public String getTenderName() {
        return tenderName;
    }

    public void setTenderName(String tenderName) {
        this.tenderName = tenderName;
    }

    public String getTenderCpcCode() {
        return tenderCpcCode;
    }

    public void setTenderCpcCode(String tenderCpcCode) {
        this.tenderCpcCode = tenderCpcCode;
    }

    public Double getTenderBudget() {
        return tenderBudget;
    }

    public void setTenderBudget(Double tenderBudget) {
        this.tenderBudget = tenderBudget;
    }

    public String getTenderProcurementRange() {
        return tenderProcurementRange;
    }

    public void setTenderProcurementRange(String tenderProcurementRange) {
        this.tenderProcurementRange = tenderProcurementRange;
    }

    public String getTenderWay() {
        return tenderWay;
    }

    public void setTenderWay(String tenderWay) {
        this.tenderWay = tenderWay;
    }

    public String getAwardWay() {
        return awardWay;
    }

    public void setAwardWay(String awardWay) {
        this.awardWay = awardWay;
    }

    public Double getTenderGovEstimate() {
        return tenderGovEstimate;
    }

    public void setTenderGovEstimate(Double tenderGovEstimate) {
        this.tenderGovEstimate = tenderGovEstimate;
    }

    public Double getAwardPrice() {
        return awardPrice;
    }

    public void setAwardPrice(Double awardPrice) {
        this.awardPrice = awardPrice;
    }

    public String getAwardPricePublic() {
        return awardPricePublic;
    }

    public void setAwardPricePublic(String awardPricePublic) {
        this.awardPricePublic = awardPricePublic;
    }

    public String getBidderId() {
        return bidderId;
    }

    public void setBidderId(String bidderId) {
        this.bidderId = bidderId;
    }

    public String getBidderSuppName() {
        return bidderSuppName;
    }

    public void setBidderSuppName(String bidderSuppName) {
        this.bidderSuppName = bidderSuppName;
    }

    public String getTenderLimitedLaw() {
        return tenderLimitedLaw;
    }

    public void setTenderLimitedLaw(String tenderLimitedLaw) {
        this.tenderLimitedLaw = tenderLimitedLaw;
    }

    public Boolean getIsPackage() {
        return isPackage;
    }

    public void setIsPackage(Boolean isPackage) {
        this.isPackage = isPackage;
    }

    public Boolean getIsMultipleAward() {
        return isMultipleAward;
    }

    public void setIsMultipleAward(Boolean isMultipleAward) {
        this.isMultipleAward = isMultipleAward;
    }

    public Boolean getIsInterEntitySupply() {
        return isInterEntitySupply;
    }

    public void setIsInterEntitySupply(Boolean isInterEntitySupply) {
        this.isInterEntitySupply = isInterEntitySupply;
    }

    public Instant getAwardNoticeDate() {
        return awardNoticeDate;
    }

    public void setAwardNoticeDate(Instant awardNoticeDate) {
        this.awardNoticeDate = awardNoticeDate;
    }

    public Instant getAwardDate() {
        return awardDate;
    }

    public void setAwardDate(Instant awardDate) {
        this.awardDate = awardDate;
    }

    public Integer getSupplierAmount() {
        return supplierAmount;
    }

    public void setSupplierAmount(Integer supplierAmount) {
        this.supplierAmount = supplierAmount;
    }

    public String getTenderStatus() {
        return tenderStatus;
    }

    public void setTenderStatus(String tenderStatus) {
        this.tenderStatus = tenderStatus;
    }

    public String getTenderContactPerson() {
        return tenderContactPerson;
    }

    public void setTenderContactPerson(String tenderContactPerson) {
        this.tenderContactPerson = tenderContactPerson;
    }

    public String getTenderContactTel() {
        return tenderContactTel;
    }

    public void setTenderContactTel(String tenderContactTel) {
        this.tenderContactTel = tenderContactTel;
    }

    public String getTenderSeq() {
        return tenderSeq;
    }

    public void setTenderSeq(String tenderSeq) {
        this.tenderSeq = tenderSeq;
    }

    public String getBidderAwardAmount() {
        return bidderAwardAmount;
    }

    public void setBidderAwardAmount(String bidderAwardAmount) {
        this.bidderAwardAmount = bidderAwardAmount;
    }

    public String getTenderIsLowestLaw() {
        return tenderIsLowestLaw;
    }

    public void setTenderIsLowestLaw(String tenderIsLowestLaw) {
        this.tenderIsLowestLaw = tenderIsLowestLaw;
    }

    public String getTenderIsChgContract() {
        return tenderIsChgContract;
    }

    public void setTenderIsChgContract(String tenderIsChgContract) {
        this.tenderIsChgContract = tenderIsChgContract;
    }

    public String getBudgetPublic() {
        return budgetPublic;
    }

    public void setBudgetPublic(String budgetPublic) {
        this.budgetPublic = budgetPublic;
    }

    public String getTenderGovestPublic() {
        return tenderGovestPublic;
    }

    public void setTenderGovestPublic(String tenderGovestPublic) {
        this.tenderGovestPublic = tenderGovestPublic;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getAwardUpdateSeq() {
        return awardUpdateSeq;
    }

    public void setAwardUpdateSeq(String awardUpdateSeq) {
        this.awardUpdateSeq = awardUpdateSeq;
    }

    public Instant getDateOfOpeningOfTenders() {
        return dateOfOpeningOfTenders;
    }

    public void setDateOfOpeningOfTenders(Instant dateOfOpeningOfTenders) {
        this.dateOfOpeningOfTenders = dateOfOpeningOfTenders;
    }

    public Instant getDateOfOriginalPublication() {
        return dateOfOriginalPublication;
    }

    public void setDateOfOriginalPublication(Instant dateOfOriginalPublication) {
        this.dateOfOriginalPublication = dateOfOriginalPublication;
    }

    public String getAwardSeq() {
        return awardSeq;
    }

    public void setAwardSeq(String awardSeq) {
        this.awardSeq = awardSeq;
    }

    public Integer getBidderAmount() {
        return bidderAmount;
    }

    public void setBidderAmount(Integer bidderAmount) {
        this.bidderAmount = bidderAmount;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getPkPmsMain() {
        return pkPmsMain;
    }

    public void setPkPmsMain(String pkPmsMain) {
        this.pkPmsMain = pkPmsMain;
    }

    public String getTenderEmail() {
        return tenderEmail;
    }

    public void setTenderEmail(String tenderEmail) {
        this.tenderEmail = tenderEmail;
    }

    public String getTenderLocationArea() {
        return tenderLocationArea;
    }

    public void setTenderLocationArea(String tenderLocationArea) {
        this.tenderLocationArea = tenderLocationArea;
    }

    public Integer getAwardItemAmount() {
        return awardItemAmount;
    }

    public void setAwardItemAmount(Integer awardItemAmount) {
        this.awardItemAmount = awardItemAmount;
    }

    public Long getSn() {
        return sn;
    }

    public void setSn(Long sn) {
        this.sn = sn;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MrpProjectPurchaseDTO)) {
            return false;
        }

        MrpProjectPurchaseDTO mrpProjectPurchaseDTO = (MrpProjectPurchaseDTO) o;
        if (this.contractCode == null) {
            return false;
        }
        return Objects.equals(this.contractCode, mrpProjectPurchaseDTO.contractCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.contractCode);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MrpProjectPurchaseDTO{" +
            "contractCode='" + getContractCode() + "'" +
            ", tenderAwardType='" + getTenderAwardType() + "'" +
            ", tenderOrgName='" + getTenderOrgName() + "'" +
            ", tenderOrgId='" + getTenderOrgId() + "'" +
            ", tenderCaseNo='" + getTenderCaseNo() + "'" +
            ", tenderName='" + getTenderName() + "'" +
            ", tenderCpcCode='" + getTenderCpcCode() + "'" +
            ", tenderBudget=" + getTenderBudget() +
            ", tenderProcurementRange='" + getTenderProcurementRange() + "'" +
            ", tenderWay='" + getTenderWay() + "'" +
            ", awardWay='" + getAwardWay() + "'" +
            ", tenderGovEstimate=" + getTenderGovEstimate() +
            ", awardPrice=" + getAwardPrice() +
            ", awardPricePublic='" + getAwardPricePublic() + "'" +
            ", bidderId='" + getBidderId() + "'" +
            ", bidderSuppName='" + getBidderSuppName() + "'" +
            ", tenderLimitedLaw='" + getTenderLimitedLaw() + "'" +
            ", isPackage='" + getIsPackage() + "'" +
            ", isMultipleAward='" + getIsMultipleAward() + "'" +
            ", isInterEntitySupply='" + getIsInterEntitySupply() + "'" +
            ", awardNoticeDate='" + getAwardNoticeDate() + "'" +
            ", awardDate='" + getAwardDate() + "'" +
            ", supplierAmount=" + getSupplierAmount() +
            ", tenderStatus='" + getTenderStatus() + "'" +
            ", tenderContactPerson='" + getTenderContactPerson() + "'" +
            ", tenderContactTel='" + getTenderContactTel() + "'" +
            ", tenderSeq='" + getTenderSeq() + "'" +
            ", bidderAwardAmount='" + getBidderAwardAmount() + "'" +
            ", tenderIsLowestLaw='" + getTenderIsLowestLaw() + "'" +
            ", tenderIsChgContract='" + getTenderIsChgContract() + "'" +
            ", budgetPublic='" + getBudgetPublic() + "'" +
            ", tenderGovestPublic='" + getTenderGovestPublic() + "'" +
            ", oid='" + getOid() + "'" +
            ", awardUpdateSeq='" + getAwardUpdateSeq() + "'" +
            ", dateOfOpeningOfTenders='" + getDateOfOpeningOfTenders() + "'" +
            ", dateOfOriginalPublication='" + getDateOfOriginalPublication() + "'" +
            ", awardSeq='" + getAwardSeq() + "'" +
            ", bidderAmount=" + getBidderAmount() +
            ", kind=" + getKind() +
            ", pkPmsMain='" + getPkPmsMain() + "'" +
            ", tenderEmail='" + getTenderEmail() + "'" +
            ", tenderLocationArea='" + getTenderLocationArea() + "'" +
            ", awardItemAmount=" + getAwardItemAmount() +
            ", sn=" + getSn() +
            ", updateId='" + getUpdateId() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", createId='" + getCreateId() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
