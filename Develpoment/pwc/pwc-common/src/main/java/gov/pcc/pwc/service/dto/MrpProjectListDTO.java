package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.MrpProjectList} entity.
 */
public class MrpProjectListDTO implements Serializable {

    @NotNull
    private Long mplId;

    @NotNull
    @Size(max = 50)
    private String contractCode;

    @NotNull
    @Size(max = 500)
    private String mainOrgName;

    @NotNull
    @Size(max = 50)
    private String mainOrgCode;

    @NotNull
    @Size(max = 50)
    private String engineeringCode;

    @NotNull
    @Size(max = 500)
    private String engineeringName;

    @Size(max = 300)
    private String host;

    @Size(max = 300)
    private String phone;

    @Size(max = 20)
    private String fax;

    private Double designBudget;

    private LocalDate designBudgetDate;

    private Double mainBudget;

    private LocalDate mainBudgetDate;

    private Double baseBudget;

    private LocalDate baseBudgetDate;

    private Double checkedBudget;

    private LocalDate checkedBudgetDate;

    private Double awardBudget;

    private LocalDate awardBudgetDate;

    @Size(max = 100)
    private String companyNo;

    @Size(max = 300)
    private String companyName;

    @Size(max = 1000)
    private String companyMemo;

    private Double benchmarking;

    @NotNull
    @Size(max = 100)
    private String localZipFile;

    private Integer kind;

    private Integer xls2;

    private Integer xls5;

    private Integer xml2;

    private Integer xml5;

    @NotNull
    private Long filesourceId;

    private Integer bidderAmount;

    @NotNull
    private Integer purchaseType;

    @Size(max = 50)
    private String awardUpdateSeq;

    @Size(max = 50)
    private String oid;

    @NotNull
    private Integer isAudit;

    @Size(max = 50)
    private String editOid;

    @Size(max = 20)
    private String updateId;

    private Instant updateDate;

    @NotNull
    @Size(max = 20)
    private String createId;

    private Instant createDate;

    public Long getMplId() {
        return mplId;
    }

    public void setMplId(Long mplId) {
        this.mplId = mplId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getMainOrgName() {
        return mainOrgName;
    }

    public void setMainOrgName(String mainOrgName) {
        this.mainOrgName = mainOrgName;
    }

    public String getMainOrgCode() {
        return mainOrgCode;
    }

    public void setMainOrgCode(String mainOrgCode) {
        this.mainOrgCode = mainOrgCode;
    }

    public String getEngineeringCode() {
        return engineeringCode;
    }

    public void setEngineeringCode(String engineeringCode) {
        this.engineeringCode = engineeringCode;
    }

    public String getEngineeringName() {
        return engineeringName;
    }

    public void setEngineeringName(String engineeringName) {
        this.engineeringName = engineeringName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Double getDesignBudget() {
        return designBudget;
    }

    public void setDesignBudget(Double designBudget) {
        this.designBudget = designBudget;
    }

    public LocalDate getDesignBudgetDate() {
        return designBudgetDate;
    }

    public void setDesignBudgetDate(LocalDate designBudgetDate) {
        this.designBudgetDate = designBudgetDate;
    }

    public Double getMainBudget() {
        return mainBudget;
    }

    public void setMainBudget(Double mainBudget) {
        this.mainBudget = mainBudget;
    }

    public LocalDate getMainBudgetDate() {
        return mainBudgetDate;
    }

    public void setMainBudgetDate(LocalDate mainBudgetDate) {
        this.mainBudgetDate = mainBudgetDate;
    }

    public Double getBaseBudget() {
        return baseBudget;
    }

    public void setBaseBudget(Double baseBudget) {
        this.baseBudget = baseBudget;
    }

    public LocalDate getBaseBudgetDate() {
        return baseBudgetDate;
    }

    public void setBaseBudgetDate(LocalDate baseBudgetDate) {
        this.baseBudgetDate = baseBudgetDate;
    }

    public Double getCheckedBudget() {
        return checkedBudget;
    }

    public void setCheckedBudget(Double checkedBudget) {
        this.checkedBudget = checkedBudget;
    }

    public LocalDate getCheckedBudgetDate() {
        return checkedBudgetDate;
    }

    public void setCheckedBudgetDate(LocalDate checkedBudgetDate) {
        this.checkedBudgetDate = checkedBudgetDate;
    }

    public Double getAwardBudget() {
        return awardBudget;
    }

    public void setAwardBudget(Double awardBudget) {
        this.awardBudget = awardBudget;
    }

    public LocalDate getAwardBudgetDate() {
        return awardBudgetDate;
    }

    public void setAwardBudgetDate(LocalDate awardBudgetDate) {
        this.awardBudgetDate = awardBudgetDate;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyMemo() {
        return companyMemo;
    }

    public void setCompanyMemo(String companyMemo) {
        this.companyMemo = companyMemo;
    }

    public Double getBenchmarking() {
        return benchmarking;
    }

    public void setBenchmarking(Double benchmarking) {
        this.benchmarking = benchmarking;
    }

    public String getLocalZipFile() {
        return localZipFile;
    }

    public void setLocalZipFile(String localZipFile) {
        this.localZipFile = localZipFile;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Integer getXls2() {
        return xls2;
    }

    public void setXls2(Integer xls2) {
        this.xls2 = xls2;
    }

    public Integer getXls5() {
        return xls5;
    }

    public void setXls5(Integer xls5) {
        this.xls5 = xls5;
    }

    public Integer getXml2() {
        return xml2;
    }

    public void setXml2(Integer xml2) {
        this.xml2 = xml2;
    }

    public Integer getXml5() {
        return xml5;
    }

    public void setXml5(Integer xml5) {
        this.xml5 = xml5;
    }

    public Long getFilesourceId() {
        return filesourceId;
    }

    public void setFilesourceId(Long filesourceId) {
        this.filesourceId = filesourceId;
    }

    public Integer getBidderAmount() {
        return bidderAmount;
    }

    public void setBidderAmount(Integer bidderAmount) {
        this.bidderAmount = bidderAmount;
    }

    public Integer getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(Integer purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getAwardUpdateSeq() {
        return awardUpdateSeq;
    }

    public void setAwardUpdateSeq(String awardUpdateSeq) {
        this.awardUpdateSeq = awardUpdateSeq;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Integer getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }

    public String getEditOid() {
        return editOid;
    }

    public void setEditOid(String editOid) {
        this.editOid = editOid;
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
        if (!(o instanceof MrpProjectListDTO)) {
            return false;
        }

        MrpProjectListDTO mrpProjectListDTO = (MrpProjectListDTO) o;
        if (this.mplId == null) {
            return false;
        }
        return Objects.equals(this.mplId, mrpProjectListDTO.mplId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mplId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MrpProjectListDTO{" +
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
