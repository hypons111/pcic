package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A MrpProjectList.
 */
@Entity
@Table(name = "mrp_project_list")
public class MrpProjectList implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Long mplId;

    @NotNull
    @Size(max = 50)
    @Column(name = "contract_code", length = 50, nullable = false)
    private String contractCode;

    @NotNull
    @Size(max = 500)
    @Column(name = "main_org_name", length = 500, nullable = false)
    private String mainOrgName;

    @NotNull
    @Size(max = 50)
    @Column(name = "main_org_code", length = 50, nullable = false)
    private String mainOrgCode;

    @NotNull
    @Size(max = 50)
    @Column(name = "engineering_code", length = 50, nullable = false)
    private String engineeringCode;

    @NotNull
    @Size(max = 500)
    @Column(name = "engineering_name", length = 500, nullable = false)
    private String engineeringName;

    @Size(max = 300)
    @Column(name = "host", length = 300)
    private String host;

    @Size(max = 300)
    @Column(name = "phone", length = 300)
    private String phone;

    @Size(max = 20)
    @Column(name = "fax", length = 20)
    private String fax;

    @Column(name = "design_budget")
    private Double designBudget;

    @Column(name = "design_budget_date")
    private LocalDate designBudgetDate;

    @Column(name = "main_budget")
    private Double mainBudget;

    @Column(name = "main_budget_date")
    private LocalDate mainBudgetDate;

    @Column(name = "base_budget")
    private Double baseBudget;

    @Column(name = "base_budget_date")
    private LocalDate baseBudgetDate;

    @Column(name = "checked_budget")
    private Double checkedBudget;

    @Column(name = "checked_budget_date")
    private LocalDate checkedBudgetDate;

    @Column(name = "award_budget")
    private Double awardBudget;

    @Column(name = "award_budget_date")
    private LocalDate awardBudgetDate;

    @Size(max = 100)
    @Column(name = "company_no", length = 100)
    private String companyNo;

    @Size(max = 300)
    @Column(name = "company_name", length = 300)
    private String companyName;

    @Size(max = 1000)
    @Column(name = "company_memo", length = 1000)
    private String companyMemo;

    @Column(name = "benchmarking")
    private Double benchmarking;

    @NotNull
    @Size(max = 100)
    @Column(name = "local_zip_file", length = 100, nullable = false)
    private String localZipFile;

    @Column(name = "kind")
    private Integer kind;

    @Column(name = "xls_2")
    private Integer xls2;

    @Column(name = "xls_5")
    private Integer xls5;

    @Column(name = "xml_2")
    private Integer xml2;

    @Column(name = "xml_5")
    private Integer xml5;

    @NotNull
    @Column(name = "filesource_id", nullable = false)
    private Long filesourceId;

    @Column(name = "bidder_amount")
    private Integer bidderAmount;

    @NotNull
    @Column(name = "purchase_type", nullable = false)
    private Integer purchaseType;

    @Size(max = 50)
    @Column(name = "award_update_seq", length = 50)
    private String awardUpdateSeq;

    @Size(max = 50)
    @Column(name = "oid", length = 50)
    private String oid;

    @NotNull
    @Column(name = "is_audit", nullable = false)
    private Integer isAudit;

    @Size(max = 50)
    @Column(name = "edit_oid", length = 50)
    private String editOid;

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

    public Long getMplId() {
        return this.mplId;
    }

    public MrpProjectList mplId(Long mplId) {
        this.setMplId(mplId);
        return this;
    }

    public void setMplId(Long mplId) {
        this.mplId = mplId;
    }

    public String getContractCode() {
        return this.contractCode;
    }

    public MrpProjectList contractCode(String contractCode) {
        this.setContractCode(contractCode);
        return this;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getMainOrgName() {
        return this.mainOrgName;
    }

    public MrpProjectList mainOrgName(String mainOrgName) {
        this.setMainOrgName(mainOrgName);
        return this;
    }

    public void setMainOrgName(String mainOrgName) {
        this.mainOrgName = mainOrgName;
    }

    public String getMainOrgCode() {
        return this.mainOrgCode;
    }

    public MrpProjectList mainOrgCode(String mainOrgCode) {
        this.setMainOrgCode(mainOrgCode);
        return this;
    }

    public void setMainOrgCode(String mainOrgCode) {
        this.mainOrgCode = mainOrgCode;
    }

    public String getEngineeringCode() {
        return this.engineeringCode;
    }

    public MrpProjectList engineeringCode(String engineeringCode) {
        this.setEngineeringCode(engineeringCode);
        return this;
    }

    public void setEngineeringCode(String engineeringCode) {
        this.engineeringCode = engineeringCode;
    }

    public String getEngineeringName() {
        return this.engineeringName;
    }

    public MrpProjectList engineeringName(String engineeringName) {
        this.setEngineeringName(engineeringName);
        return this;
    }

    public void setEngineeringName(String engineeringName) {
        this.engineeringName = engineeringName;
    }

    public String getHost() {
        return this.host;
    }

    public MrpProjectList host(String host) {
        this.setHost(host);
        return this;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPhone() {
        return this.phone;
    }

    public MrpProjectList phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return this.fax;
    }

    public MrpProjectList fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Double getDesignBudget() {
        return this.designBudget;
    }

    public MrpProjectList designBudget(Double designBudget) {
        this.setDesignBudget(designBudget);
        return this;
    }

    public void setDesignBudget(Double designBudget) {
        this.designBudget = designBudget;
    }

    public LocalDate getDesignBudgetDate() {
        return this.designBudgetDate;
    }

    public MrpProjectList designBudgetDate(LocalDate designBudgetDate) {
        this.setDesignBudgetDate(designBudgetDate);
        return this;
    }

    public void setDesignBudgetDate(LocalDate designBudgetDate) {
        this.designBudgetDate = designBudgetDate;
    }

    public Double getMainBudget() {
        return this.mainBudget;
    }

    public MrpProjectList mainBudget(Double mainBudget) {
        this.setMainBudget(mainBudget);
        return this;
    }

    public void setMainBudget(Double mainBudget) {
        this.mainBudget = mainBudget;
    }

    public LocalDate getMainBudgetDate() {
        return this.mainBudgetDate;
    }

    public MrpProjectList mainBudgetDate(LocalDate mainBudgetDate) {
        this.setMainBudgetDate(mainBudgetDate);
        return this;
    }

    public void setMainBudgetDate(LocalDate mainBudgetDate) {
        this.mainBudgetDate = mainBudgetDate;
    }

    public Double getBaseBudget() {
        return this.baseBudget;
    }

    public MrpProjectList baseBudget(Double baseBudget) {
        this.setBaseBudget(baseBudget);
        return this;
    }

    public void setBaseBudget(Double baseBudget) {
        this.baseBudget = baseBudget;
    }

    public LocalDate getBaseBudgetDate() {
        return this.baseBudgetDate;
    }

    public MrpProjectList baseBudgetDate(LocalDate baseBudgetDate) {
        this.setBaseBudgetDate(baseBudgetDate);
        return this;
    }

    public void setBaseBudgetDate(LocalDate baseBudgetDate) {
        this.baseBudgetDate = baseBudgetDate;
    }

    public Double getCheckedBudget() {
        return this.checkedBudget;
    }

    public MrpProjectList checkedBudget(Double checkedBudget) {
        this.setCheckedBudget(checkedBudget);
        return this;
    }

    public void setCheckedBudget(Double checkedBudget) {
        this.checkedBudget = checkedBudget;
    }

    public LocalDate getCheckedBudgetDate() {
        return this.checkedBudgetDate;
    }

    public MrpProjectList checkedBudgetDate(LocalDate checkedBudgetDate) {
        this.setCheckedBudgetDate(checkedBudgetDate);
        return this;
    }

    public void setCheckedBudgetDate(LocalDate checkedBudgetDate) {
        this.checkedBudgetDate = checkedBudgetDate;
    }

    public Double getAwardBudget() {
        return this.awardBudget;
    }

    public MrpProjectList awardBudget(Double awardBudget) {
        this.setAwardBudget(awardBudget);
        return this;
    }

    public void setAwardBudget(Double awardBudget) {
        this.awardBudget = awardBudget;
    }

    public LocalDate getAwardBudgetDate() {
        return this.awardBudgetDate;
    }

    public MrpProjectList awardBudgetDate(LocalDate awardBudgetDate) {
        this.setAwardBudgetDate(awardBudgetDate);
        return this;
    }

    public void setAwardBudgetDate(LocalDate awardBudgetDate) {
        this.awardBudgetDate = awardBudgetDate;
    }

    public String getCompanyNo() {
        return this.companyNo;
    }

    public MrpProjectList companyNo(String companyNo) {
        this.setCompanyNo(companyNo);
        return this;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public MrpProjectList companyName(String companyName) {
        this.setCompanyName(companyName);
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyMemo() {
        return this.companyMemo;
    }

    public MrpProjectList companyMemo(String companyMemo) {
        this.setCompanyMemo(companyMemo);
        return this;
    }

    public void setCompanyMemo(String companyMemo) {
        this.companyMemo = companyMemo;
    }

    public Double getBenchmarking() {
        return this.benchmarking;
    }

    public MrpProjectList benchmarking(Double benchmarking) {
        this.setBenchmarking(benchmarking);
        return this;
    }

    public void setBenchmarking(Double benchmarking) {
        this.benchmarking = benchmarking;
    }

    public String getLocalZipFile() {
        return this.localZipFile;
    }

    public MrpProjectList localZipFile(String localZipFile) {
        this.setLocalZipFile(localZipFile);
        return this;
    }

    public void setLocalZipFile(String localZipFile) {
        this.localZipFile = localZipFile;
    }

    public Integer getKind() {
        return this.kind;
    }

    public MrpProjectList kind(Integer kind) {
        this.setKind(kind);
        return this;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Integer getXls2() {
        return this.xls2;
    }

    public MrpProjectList xls2(Integer xls2) {
        this.setXls2(xls2);
        return this;
    }

    public void setXls2(Integer xls2) {
        this.xls2 = xls2;
    }

    public Integer getXls5() {
        return this.xls5;
    }

    public MrpProjectList xls5(Integer xls5) {
        this.setXls5(xls5);
        return this;
    }

    public void setXls5(Integer xls5) {
        this.xls5 = xls5;
    }

    public Integer getXml2() {
        return this.xml2;
    }

    public MrpProjectList xml2(Integer xml2) {
        this.setXml2(xml2);
        return this;
    }

    public void setXml2(Integer xml2) {
        this.xml2 = xml2;
    }

    public Integer getXml5() {
        return this.xml5;
    }

    public MrpProjectList xml5(Integer xml5) {
        this.setXml5(xml5);
        return this;
    }

    public void setXml5(Integer xml5) {
        this.xml5 = xml5;
    }

    public Long getFilesourceId() {
        return this.filesourceId;
    }

    public MrpProjectList filesourceId(Long filesourceId) {
        this.setFilesourceId(filesourceId);
        return this;
    }

    public void setFilesourceId(Long filesourceId) {
        this.filesourceId = filesourceId;
    }

    public Integer getBidderAmount() {
        return this.bidderAmount;
    }

    public MrpProjectList bidderAmount(Integer bidderAmount) {
        this.setBidderAmount(bidderAmount);
        return this;
    }

    public void setBidderAmount(Integer bidderAmount) {
        this.bidderAmount = bidderAmount;
    }

    public Integer getPurchaseType() {
        return this.purchaseType;
    }

    public MrpProjectList purchaseType(Integer purchaseType) {
        this.setPurchaseType(purchaseType);
        return this;
    }

    public void setPurchaseType(Integer purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getAwardUpdateSeq() {
        return this.awardUpdateSeq;
    }

    public MrpProjectList awardUpdateSeq(String awardUpdateSeq) {
        this.setAwardUpdateSeq(awardUpdateSeq);
        return this;
    }

    public void setAwardUpdateSeq(String awardUpdateSeq) {
        this.awardUpdateSeq = awardUpdateSeq;
    }

    public String getOid() {
        return this.oid;
    }

    public MrpProjectList oid(String oid) {
        this.setOid(oid);
        return this;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Integer getIsAudit() {
        return this.isAudit;
    }

    public MrpProjectList isAudit(Integer isAudit) {
        this.setIsAudit(isAudit);
        return this;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }

    public String getEditOid() {
        return this.editOid;
    }

    public MrpProjectList editOid(String editOid) {
        this.setEditOid(editOid);
        return this;
    }

    public void setEditOid(String editOid) {
        this.editOid = editOid;
    }

    public String getUpdateId() {
        return this.updateId;
    }

    public MrpProjectList updateId(String updateId) {
        this.setUpdateId(updateId);
        return this;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public MrpProjectList updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateId() {
        return this.createId;
    }

    public MrpProjectList createId(String createId) {
        this.setCreateId(createId);
        return this;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public MrpProjectList createDate(Instant createDate) {
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
        if (!(o instanceof MrpProjectList)) {
            return false;
        }
        return mplId != null && mplId.equals(((MrpProjectList) o).mplId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MrpProjectList{" +
            "mplId=" + getMplId() +
            ", contractCode='" + getContractCode() + "'" +
            ", mainOrgName='" + getMainOrgName() + "'" +
            ", mainOrgCode='" + getMainOrgCode() + "'" +
            ", engineeringCode='" + getEngineeringCode() + "'" +
            ", engineeringName='" + getEngineeringName() + "'" +
            ", host='" + getHost() + "'" +
            ", phone='" + getPhone() + "'" +
            ", fax='" + getFax() + "'" +
            ", designBudget=" + getDesignBudget() +
            ", designBudgetDate='" + getDesignBudgetDate() + "'" +
            ", mainBudget=" + getMainBudget() +
            ", mainBudgetDate='" + getMainBudgetDate() + "'" +
            ", baseBudget=" + getBaseBudget() +
            ", baseBudgetDate='" + getBaseBudgetDate() + "'" +
            ", checkedBudget=" + getCheckedBudget() +
            ", checkedBudgetDate='" + getCheckedBudgetDate() + "'" +
            ", awardBudget=" + getAwardBudget() +
            ", awardBudgetDate='" + getAwardBudgetDate() + "'" +
            ", companyNo='" + getCompanyNo() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", companyMemo='" + getCompanyMemo() + "'" +
            ", benchmarking=" + getBenchmarking() +
            ", localZipFile='" + getLocalZipFile() + "'" +
            ", kind=" + getKind() +
            ", xls2=" + getXls2() +
            ", xls5=" + getXls5() +
            ", xml2=" + getXml2() +
            ", xml5=" + getXml5() +
            ", filesourceId=" + getFilesourceId() +
            ", bidderAmount=" + getBidderAmount() +
            ", purchaseType=" + getPurchaseType() +
            ", awardUpdateSeq='" + getAwardUpdateSeq() + "'" +
            ", oid='" + getOid() + "'" +
            ", isAudit=" + getIsAudit() +
            ", editOid='" + getEditOid() + "'" +
            ", updateId='" + getUpdateId() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", createId='" + getCreateId() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
