package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EngCase} entity.
 */
public class EngCaseDTO implements Serializable {

    @NotNull
    @Size(max = 20)
    private String engCaseId;

    @Size(max = 100)
    private String caseNo;

    @Size(max = 20)
    private String tenderNo;

    @Size(max = 20)
    private String awardNo;

    @Size(max = 1)
    private String caseType;

    @NotNull
    @Size(max = 300)
    private String caseName;

    @Size(max = 100)
    private String orgId;

    @Size(max = 50)
    private String orgName;

    @Size(max = 100)
    private String execOrgId;

    @Size(max = 50)
    private String execOrgName;

    @Size(max = 200)
    private String contactPerson;

    @Size(max = 50)
    private String contactTel;

    @Size(max = 600)
    private String entrustItem;

    private LocalDate tenderDate;

    private BigDecimal tenderPrice;

    @NotNull
    private LocalDate execDateStart;

    @NotNull
    private LocalDate execDateEnd;

    @NotNull
    @Size(max = 20)
    private String projectType;

    @NotNull
    @Size(max = 100)
    private String recordRangeList;

    @Size(max = 100)
    private String recordRangeOther;

    @NotNull
    @Size(max = 50)
    private String recordItemsList;

    @Size(max = 100)
    private String recordItemsOther;

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

    public String getEngCaseId() {
        return engCaseId;
    }

    public void setEngCaseId(String engCaseId) {
        this.engCaseId = engCaseId;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getTenderNo() {
        return tenderNo;
    }

    public void setTenderNo(String tenderNo) {
        this.tenderNo = tenderNo;
    }

    public String getAwardNo() {
        return awardNo;
    }

    public void setAwardNo(String awardNo) {
        this.awardNo = awardNo;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getExecOrgId() {
        return execOrgId;
    }

    public void setExecOrgId(String execOrgId) {
        this.execOrgId = execOrgId;
    }

    public String getExecOrgName() {
        return execOrgName;
    }

    public void setExecOrgName(String execOrgName) {
        this.execOrgName = execOrgName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getEntrustItem() {
        return entrustItem;
    }

    public void setEntrustItem(String entrustItem) {
        this.entrustItem = entrustItem;
    }

    public LocalDate getTenderDate() {
        return tenderDate;
    }

    public void setTenderDate(LocalDate tenderDate) {
        this.tenderDate = tenderDate;
    }

    public BigDecimal getTenderPrice() {
        return tenderPrice;
    }

    public void setTenderPrice(BigDecimal tenderPrice) {
        this.tenderPrice = tenderPrice;
    }

    public LocalDate getExecDateStart() {
        return execDateStart;
    }

    public void setExecDateStart(LocalDate execDateStart) {
        this.execDateStart = execDateStart;
    }

    public LocalDate getExecDateEnd() {
        return execDateEnd;
    }

    public void setExecDateEnd(LocalDate execDateEnd) {
        this.execDateEnd = execDateEnd;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getRecordRangeList() {
        return recordRangeList;
    }

    public void setRecordRangeList(String recordRangeList) {
        this.recordRangeList = recordRangeList;
    }

    public String getRecordRangeOther() {
        return recordRangeOther;
    }

    public void setRecordRangeOther(String recordRangeOther) {
        this.recordRangeOther = recordRangeOther;
    }

    public String getRecordItemsList() {
        return recordItemsList;
    }

    public void setRecordItemsList(String recordItemsList) {
        this.recordItemsList = recordItemsList;
    }

    public String getRecordItemsOther() {
        return recordItemsOther;
    }

    public void setRecordItemsOther(String recordItemsOther) {
        this.recordItemsOther = recordItemsOther;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngCaseDTO)) {
            return false;
        }

        EngCaseDTO engCaseDTO = (EngCaseDTO) o;
        if (this.engCaseId == null) {
            return false;
        }
        return Objects.equals(this.engCaseId, engCaseDTO.engCaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.engCaseId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngCaseDTO{" +
            "engCaseId='" + getEngCaseId() + "'" +
            ", caseNo='" + getCaseNo() + "'" +
            ", tenderNo='" + getTenderNo() + "'" +
            ", awardNo='" + getAwardNo() + "'" +
            ", caseType='" + getCaseType() + "'" +
            ", caseName='" + getCaseName() + "'" +
            ", orgId='" + getOrgId() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", execOrgId='" + getExecOrgId() + "'" +
            ", execOrgName='" + getExecOrgName() + "'" +
            ", contactPerson='" + getContactPerson() + "'" +
            ", contactTel='" + getContactTel() + "'" +
            ", entrustItem='" + getEntrustItem() + "'" +
            ", tenderDate='" + getTenderDate() + "'" +
            ", tenderPrice=" + getTenderPrice() +
            ", execDateStart='" + getExecDateStart() + "'" +
            ", execDateEnd='" + getExecDateEnd() + "'" +
            ", projectType='" + getProjectType() + "'" +
            ", recordRangeList='" + getRecordRangeList() + "'" +
            ", recordRangeOther='" + getRecordRangeOther() + "'" +
            ", recordItemsList='" + getRecordItemsList() + "'" +
            ", recordItemsOther='" + getRecordItemsOther() + "'" +
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
