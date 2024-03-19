package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A MrpProjectPurchase.
 */
@Entity
@Table(name = "mrp_project_purchase")
public class MrpProjectPurchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 50)
    @Id
    @Column(name = "contract_code", length = 50, nullable = false)
    private String contractCode;

    @Size(max = 100)
    @Column(name = "tender_award_type", length = 100)
    private String tenderAwardType;

    @NotNull
    @Size(max = 100)
    @Column(name = "tender_org_name", length = 100, nullable = false)
    private String tenderOrgName;

    @NotNull
    @Size(max = 30)
    @Column(name = "tender_org_id", length = 30, nullable = false)
    private String tenderOrgId;

    @NotNull
    @Size(max = 50)
    @Column(name = "tender_case_no", length = 50, nullable = false)
    private String tenderCaseNo;

    @NotNull
    @Size(max = 200)
    @Column(name = "tender_name", length = 200, nullable = false)
    private String tenderName;

    @Size(max = 100)
    @Column(name = "tender_cpc_code", length = 100)
    private String tenderCpcCode;

    @Column(name = "tender_budget")
    private Double tenderBudget;

    @Size(max = 100)
    @Column(name = "tender_procurement_range", length = 100)
    private String tenderProcurementRange;

    @Size(max = 100)
    @Column(name = "tender_way", length = 100)
    private String tenderWay;

    @Size(max = 100)
    @Column(name = "award_way", length = 100)
    private String awardWay;

    @Column(name = "tender_gov_estimate")
    private Double tenderGovEstimate;

    @Column(name = "award_price")
    private Double awardPrice;

    @Size(max = 100)
    @Column(name = "award_price_public", length = 100)
    private String awardPricePublic;

    @Size(max = 20)
    @Column(name = "bidder_id", length = 20)
    private String bidderId;

    @Size(max = 100)
    @Column(name = "bidder_supp_name", length = 100)
    private String bidderSuppName;

    @Size(max = 100)
    @Column(name = "tender_limited_law", length = 100)
    private String tenderLimitedLaw;

    @Column(name = "is_package")
    private Boolean isPackage;

    @Column(name = "is_multiple_award")
    private Boolean isMultipleAward;

    @Column(name = "is_inter_entity_supply")
    private Boolean isInterEntitySupply;

    @Column(name = "award_notice_date")
    private Instant awardNoticeDate;

    @Column(name = "award_date")
    private Instant awardDate;

    @Column(name = "supplier_amount")
    private Integer supplierAmount;

    @NotNull
    @Size(max = 100)
    @Column(name = "tender_status", length = 100, nullable = false)
    private String tenderStatus;

    @Size(max = 100)
    @Column(name = "tender_contact_person", length = 100)
    private String tenderContactPerson;

    @Size(max = 100)
    @Column(name = "tender_contact_tel", length = 100)
    private String tenderContactTel;

    @NotNull
    @Size(max = 100)
    @Column(name = "tender_seq", length = 100, nullable = false)
    private String tenderSeq;

    @Size(max = 100)
    @Column(name = "bidder_award_amount", length = 100)
    private String bidderAwardAmount;

    @Size(max = 100)
    @Column(name = "tender_is_lowest_law", length = 100)
    private String tenderIsLowestLaw;

    @Size(max = 100)
    @Column(name = "tender_is_chg_contract", length = 100)
    private String tenderIsChgContract;

    @Size(max = 100)
    @Column(name = "budget_public", length = 100)
    private String budgetPublic;

    @Size(max = 100)
    @Column(name = "tender_govest_public", length = 100)
    private String tenderGovestPublic;

    @Size(max = 50)
    @Column(name = "oid", length = 50)
    private String oid;

    @Size(max = 50)
    @Column(name = "award_update_seq", length = 50)
    private String awardUpdateSeq;

    @Column(name = "date_of_opening_of_tenders")
    private Instant dateOfOpeningOfTenders;

    @Column(name = "date_of_original_publication")
    private Instant dateOfOriginalPublication;

    @NotNull
    @Size(max = 10)
    @Column(name = "award_seq", length = 10, nullable = false)
    private String awardSeq;

    @Column(name = "bidder_amount")
    private Integer bidderAmount;

    @NotNull
    @Column(name = "kind", nullable = false)
    private Integer kind;

    @Size(max = 50)
    @Column(name = "pk_pms_main", length = 50)
    private String pkPmsMain;

    @Size(max = 100)
    @Column(name = "tender_email", length = 100)
    private String tenderEmail;

    @Size(max = 1000)
    @Column(name = "tender_location_area", length = 1000)
    private String tenderLocationArea;

    @Column(name = "award_item_amount")
    private Integer awardItemAmount;

    @NotNull
    @Column(name = "sn", nullable = false)
    private Long sn;

    @Size(max = 20)
    @Column(name = "update_id", length = 20)
    private String updateId;

    @Column(name = "update_date")
    private Instant updateDate;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_id", length = 20, nullable = false)
    private String createId;

    @Column(name = "create_date")
    private Instant createDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getContractCode() {
        return this.contractCode;
    }

    public MrpProjectPurchase contractCode(String contractCode) {
        this.setContractCode(contractCode);
        return this;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getTenderAwardType() {
        return this.tenderAwardType;
    }

    public MrpProjectPurchase tenderAwardType(String tenderAwardType) {
        this.setTenderAwardType(tenderAwardType);
        return this;
    }

    public void setTenderAwardType(String tenderAwardType) {
        this.tenderAwardType = tenderAwardType;
    }

    public String getTenderOrgName() {
        return this.tenderOrgName;
    }

    public MrpProjectPurchase tenderOrgName(String tenderOrgName) {
        this.setTenderOrgName(tenderOrgName);
        return this;
    }

    public void setTenderOrgName(String tenderOrgName) {
        this.tenderOrgName = tenderOrgName;
    }

    public String getTenderOrgId() {
        return this.tenderOrgId;
    }

    public MrpProjectPurchase tenderOrgId(String tenderOrgId) {
        this.setTenderOrgId(tenderOrgId);
        return this;
    }

    public void setTenderOrgId(String tenderOrgId) {
        this.tenderOrgId = tenderOrgId;
    }

    public String getTenderCaseNo() {
        return this.tenderCaseNo;
    }

    public MrpProjectPurchase tenderCaseNo(String tenderCaseNo) {
        this.setTenderCaseNo(tenderCaseNo);
        return this;
    }

    public void setTenderCaseNo(String tenderCaseNo) {
        this.tenderCaseNo = tenderCaseNo;
    }

    public String getTenderName() {
        return this.tenderName;
    }

    public MrpProjectPurchase tenderName(String tenderName) {
        this.setTenderName(tenderName);
        return this;
    }

    public void setTenderName(String tenderName) {
        this.tenderName = tenderName;
    }

    public String getTenderCpcCode() {
        return this.tenderCpcCode;
    }

    public MrpProjectPurchase tenderCpcCode(String tenderCpcCode) {
        this.setTenderCpcCode(tenderCpcCode);
        return this;
    }

    public void setTenderCpcCode(String tenderCpcCode) {
        this.tenderCpcCode = tenderCpcCode;
    }

    public Double getTenderBudget() {
        return this.tenderBudget;
    }

    public MrpProjectPurchase tenderBudget(Double tenderBudget) {
        this.setTenderBudget(tenderBudget);
        return this;
    }

    public void setTenderBudget(Double tenderBudget) {
        this.tenderBudget = tenderBudget;
    }

    public String getTenderProcurementRange() {
        return this.tenderProcurementRange;
    }

    public MrpProjectPurchase tenderProcurementRange(String tenderProcurementRange) {
        this.setTenderProcurementRange(tenderProcurementRange);
        return this;
    }

    public void setTenderProcurementRange(String tenderProcurementRange) {
        this.tenderProcurementRange = tenderProcurementRange;
    }

    public String getTenderWay() {
        return this.tenderWay;
    }

    public MrpProjectPurchase tenderWay(String tenderWay) {
        this.setTenderWay(tenderWay);
        return this;
    }

    public void setTenderWay(String tenderWay) {
        this.tenderWay = tenderWay;
    }

    public String getAwardWay() {
        return this.awardWay;
    }

    public MrpProjectPurchase awardWay(String awardWay) {
        this.setAwardWay(awardWay);
        return this;
    }

    public void setAwardWay(String awardWay) {
        this.awardWay = awardWay;
    }

    public Double getTenderGovEstimate() {
        return this.tenderGovEstimate;
    }

    public MrpProjectPurchase tenderGovEstimate(Double tenderGovEstimate) {
        this.setTenderGovEstimate(tenderGovEstimate);
        return this;
    }

    public void setTenderGovEstimate(Double tenderGovEstimate) {
        this.tenderGovEstimate = tenderGovEstimate;
    }

    public Double getAwardPrice() {
        return this.awardPrice;
    }

    public MrpProjectPurchase awardPrice(Double awardPrice) {
        this.setAwardPrice(awardPrice);
        return this;
    }

    public void setAwardPrice(Double awardPrice) {
        this.awardPrice = awardPrice;
    }

    public String getAwardPricePublic() {
        return this.awardPricePublic;
    }

    public MrpProjectPurchase awardPricePublic(String awardPricePublic) {
        this.setAwardPricePublic(awardPricePublic);
        return this;
    }

    public void setAwardPricePublic(String awardPricePublic) {
        this.awardPricePublic = awardPricePublic;
    }

    public String getBidderId() {
        return this.bidderId;
    }

    public MrpProjectPurchase bidderId(String bidderId) {
        this.setBidderId(bidderId);
        return this;
    }

    public void setBidderId(String bidderId) {
        this.bidderId = bidderId;
    }

    public String getBidderSuppName() {
        return this.bidderSuppName;
    }

    public MrpProjectPurchase bidderSuppName(String bidderSuppName) {
        this.setBidderSuppName(bidderSuppName);
        return this;
    }

    public void setBidderSuppName(String bidderSuppName) {
        this.bidderSuppName = bidderSuppName;
    }

    public String getTenderLimitedLaw() {
        return this.tenderLimitedLaw;
    }

    public MrpProjectPurchase tenderLimitedLaw(String tenderLimitedLaw) {
        this.setTenderLimitedLaw(tenderLimitedLaw);
        return this;
    }

    public void setTenderLimitedLaw(String tenderLimitedLaw) {
        this.tenderLimitedLaw = tenderLimitedLaw;
    }

    public Boolean getIsPackage() {
        return this.isPackage;
    }

    public MrpProjectPurchase isPackage(Boolean isPackage) {
        this.setIsPackage(isPackage);
        return this;
    }

    public void setIsPackage(Boolean isPackage) {
        this.isPackage = isPackage;
    }

    public Boolean getIsMultipleAward() {
        return this.isMultipleAward;
    }

    public MrpProjectPurchase isMultipleAward(Boolean isMultipleAward) {
        this.setIsMultipleAward(isMultipleAward);
        return this;
    }

    public void setIsMultipleAward(Boolean isMultipleAward) {
        this.isMultipleAward = isMultipleAward;
    }

    public Boolean getIsInterEntitySupply() {
        return this.isInterEntitySupply;
    }

    public MrpProjectPurchase isInterEntitySupply(Boolean isInterEntitySupply) {
        this.setIsInterEntitySupply(isInterEntitySupply);
        return this;
    }

    public void setIsInterEntitySupply(Boolean isInterEntitySupply) {
        this.isInterEntitySupply = isInterEntitySupply;
    }

    public Instant getAwardNoticeDate() {
        return this.awardNoticeDate;
    }

    public MrpProjectPurchase awardNoticeDate(Instant awardNoticeDate) {
        this.setAwardNoticeDate(awardNoticeDate);
        return this;
    }

    public void setAwardNoticeDate(Instant awardNoticeDate) {
        this.awardNoticeDate = awardNoticeDate;
    }

    public Instant getAwardDate() {
        return this.awardDate;
    }

    public MrpProjectPurchase awardDate(Instant awardDate) {
        this.setAwardDate(awardDate);
        return this;
    }

    public void setAwardDate(Instant awardDate) {
        this.awardDate = awardDate;
    }

    public Integer getSupplierAmount() {
        return this.supplierAmount;
    }

    public MrpProjectPurchase supplierAmount(Integer supplierAmount) {
        this.setSupplierAmount(supplierAmount);
        return this;
    }

    public void setSupplierAmount(Integer supplierAmount) {
        this.supplierAmount = supplierAmount;
    }

    public String getTenderStatus() {
        return this.tenderStatus;
    }

    public MrpProjectPurchase tenderStatus(String tenderStatus) {
        this.setTenderStatus(tenderStatus);
        return this;
    }

    public void setTenderStatus(String tenderStatus) {
        this.tenderStatus = tenderStatus;
    }

    public String getTenderContactPerson() {
        return this.tenderContactPerson;
    }

    public MrpProjectPurchase tenderContactPerson(String tenderContactPerson) {
        this.setTenderContactPerson(tenderContactPerson);
        return this;
    }

    public void setTenderContactPerson(String tenderContactPerson) {
        this.tenderContactPerson = tenderContactPerson;
    }

    public String getTenderContactTel() {
        return this.tenderContactTel;
    }

    public MrpProjectPurchase tenderContactTel(String tenderContactTel) {
        this.setTenderContactTel(tenderContactTel);
        return this;
    }

    public void setTenderContactTel(String tenderContactTel) {
        this.tenderContactTel = tenderContactTel;
    }

    public String getTenderSeq() {
        return this.tenderSeq;
    }

    public MrpProjectPurchase tenderSeq(String tenderSeq) {
        this.setTenderSeq(tenderSeq);
        return this;
    }

    public void setTenderSeq(String tenderSeq) {
        this.tenderSeq = tenderSeq;
    }

    public String getBidderAwardAmount() {
        return this.bidderAwardAmount;
    }

    public MrpProjectPurchase bidderAwardAmount(String bidderAwardAmount) {
        this.setBidderAwardAmount(bidderAwardAmount);
        return this;
    }

    public void setBidderAwardAmount(String bidderAwardAmount) {
        this.bidderAwardAmount = bidderAwardAmount;
    }

    public String getTenderIsLowestLaw() {
        return this.tenderIsLowestLaw;
    }

    public MrpProjectPurchase tenderIsLowestLaw(String tenderIsLowestLaw) {
        this.setTenderIsLowestLaw(tenderIsLowestLaw);
        return this;
    }

    public void setTenderIsLowestLaw(String tenderIsLowestLaw) {
        this.tenderIsLowestLaw = tenderIsLowestLaw;
    }

    public String getTenderIsChgContract() {
        return this.tenderIsChgContract;
    }

    public MrpProjectPurchase tenderIsChgContract(String tenderIsChgContract) {
        this.setTenderIsChgContract(tenderIsChgContract);
        return this;
    }

    public void setTenderIsChgContract(String tenderIsChgContract) {
        this.tenderIsChgContract = tenderIsChgContract;
    }

    public String getBudgetPublic() {
        return this.budgetPublic;
    }

    public MrpProjectPurchase budgetPublic(String budgetPublic) {
        this.setBudgetPublic(budgetPublic);
        return this;
    }

    public void setBudgetPublic(String budgetPublic) {
        this.budgetPublic = budgetPublic;
    }

    public String getTenderGovestPublic() {
        return this.tenderGovestPublic;
    }

    public MrpProjectPurchase tenderGovestPublic(String tenderGovestPublic) {
        this.setTenderGovestPublic(tenderGovestPublic);
        return this;
    }

    public void setTenderGovestPublic(String tenderGovestPublic) {
        this.tenderGovestPublic = tenderGovestPublic;
    }

    public String getOid() {
        return this.oid;
    }

    public MrpProjectPurchase oid(String oid) {
        this.setOid(oid);
        return this;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getAwardUpdateSeq() {
        return this.awardUpdateSeq;
    }

    public MrpProjectPurchase awardUpdateSeq(String awardUpdateSeq) {
        this.setAwardUpdateSeq(awardUpdateSeq);
        return this;
    }

    public void setAwardUpdateSeq(String awardUpdateSeq) {
        this.awardUpdateSeq = awardUpdateSeq;
    }

    public Instant getDateOfOpeningOfTenders() {
        return this.dateOfOpeningOfTenders;
    }

    public MrpProjectPurchase dateOfOpeningOfTenders(Instant dateOfOpeningOfTenders) {
        this.setDateOfOpeningOfTenders(dateOfOpeningOfTenders);
        return this;
    }

    public void setDateOfOpeningOfTenders(Instant dateOfOpeningOfTenders) {
        this.dateOfOpeningOfTenders = dateOfOpeningOfTenders;
    }

    public Instant getDateOfOriginalPublication() {
        return this.dateOfOriginalPublication;
    }

    public MrpProjectPurchase dateOfOriginalPublication(Instant dateOfOriginalPublication) {
        this.setDateOfOriginalPublication(dateOfOriginalPublication);
        return this;
    }

    public void setDateOfOriginalPublication(Instant dateOfOriginalPublication) {
        this.dateOfOriginalPublication = dateOfOriginalPublication;
    }

    public String getAwardSeq() {
        return this.awardSeq;
    }

    public MrpProjectPurchase awardSeq(String awardSeq) {
        this.setAwardSeq(awardSeq);
        return this;
    }

    public void setAwardSeq(String awardSeq) {
        this.awardSeq = awardSeq;
    }

    public Integer getBidderAmount() {
        return this.bidderAmount;
    }

    public MrpProjectPurchase bidderAmount(Integer bidderAmount) {
        this.setBidderAmount(bidderAmount);
        return this;
    }

    public void setBidderAmount(Integer bidderAmount) {
        this.bidderAmount = bidderAmount;
    }

    public Integer getKind() {
        return this.kind;
    }

    public MrpProjectPurchase kind(Integer kind) {
        this.setKind(kind);
        return this;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getPkPmsMain() {
        return this.pkPmsMain;
    }

    public MrpProjectPurchase pkPmsMain(String pkPmsMain) {
        this.setPkPmsMain(pkPmsMain);
        return this;
    }

    public void setPkPmsMain(String pkPmsMain) {
        this.pkPmsMain = pkPmsMain;
    }

    public String getTenderEmail() {
        return this.tenderEmail;
    }

    public MrpProjectPurchase tenderEmail(String tenderEmail) {
        this.setTenderEmail(tenderEmail);
        return this;
    }

    public void setTenderEmail(String tenderEmail) {
        this.tenderEmail = tenderEmail;
    }

    public String getTenderLocationArea() {
        return this.tenderLocationArea;
    }

    public MrpProjectPurchase tenderLocationArea(String tenderLocationArea) {
        this.setTenderLocationArea(tenderLocationArea);
        return this;
    }

    public void setTenderLocationArea(String tenderLocationArea) {
        this.tenderLocationArea = tenderLocationArea;
    }

    public Integer getAwardItemAmount() {
        return this.awardItemAmount;
    }

    public MrpProjectPurchase awardItemAmount(Integer awardItemAmount) {
        this.setAwardItemAmount(awardItemAmount);
        return this;
    }

    public void setAwardItemAmount(Integer awardItemAmount) {
        this.awardItemAmount = awardItemAmount;
    }

    public Long getSn() {
        return this.sn;
    }

    public MrpProjectPurchase sn(Long sn) {
        this.setSn(sn);
        return this;
    }

    public void setSn(Long sn) {
        this.sn = sn;
    }

    public String getUpdateId() {
        return this.updateId;
    }

    public MrpProjectPurchase updateId(String updateId) {
        this.setUpdateId(updateId);
        return this;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public MrpProjectPurchase updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateId() {
        return this.createId;
    }

    public MrpProjectPurchase createId(String createId) {
        this.setCreateId(createId);
        return this;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public MrpProjectPurchase createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MrpProjectPurchase)) {
            return false;
        }
        return contractCode != null && contractCode.equals(((MrpProjectPurchase) o).contractCode);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MrpProjectPurchase{" +
            "contractCode=" + getContractCode() +
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
