package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A EngCase.
 */
@Entity
@Table(name = "eng_case")
public class EngCase implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "eng_case_id", length = 20, nullable = false)
    private String engCaseId;

    @Size(max = 100)
    @Column(name = "case_no", length = 100)
    private String caseNo;

    @Size(max = 20)
    @Column(name = "tender_no", length = 20)
    private String tenderNo;

    @Size(max = 20)
    @Column(name = "award_no", length = 20)
    private String awardNo;

    @Size(max = 1)
    @Column(name = "case_type", length = 1)
    private String caseType;

    @NotNull
    @Size(max = 300)
    @Column(name = "case_name", length = 300, nullable = false)
    private String caseName;

    @Size(max = 100)
    @Column(name = "org_id", length = 100)
    private String orgId;

    @Size(max = 50)
    @Column(name = "org_name", length = 50)
    private String orgName;

    @Size(max = 100)
    @Column(name = "exec_org_id", length = 100)
    private String execOrgId;

    @Size(max = 50)
    @Column(name = "exec_org_name", length = 50)
    private String execOrgName;

    @Size(max = 200)
    @Column(name = "contact_person", length = 200)
    private String contactPerson;

    @Size(max = 50)
    @Column(name = "contact_tel", length = 50)
    private String contactTel;

    @Size(max = 600)
    @Column(name = "entrust_item", length = 600)
    private String entrustItem;

    @Column(name = "tender_date")
    private LocalDate tenderDate;

    @Column(name = "tender_price", precision = 21, scale = 2)
    private BigDecimal tenderPrice;

    @NotNull
    @Column(name = "exec_date_start", nullable = false)
    private LocalDate execDateStart;

    @NotNull
    @Column(name = "exec_date_end", nullable = false)
    private LocalDate execDateEnd;

    @NotNull
    @Size(max = 20)
    @Column(name = "project_type", length = 20, nullable = false)
    private String projectType;

    @NotNull
    @Size(max = 100)
    @Column(name = "record_range_list", length = 100, nullable = false)
    private String recordRangeList;

    @Size(max = 100)
    @Column(name = "record_range_other", length = 100)
    private String recordRangeOther;

    @NotNull
    @Size(max = 50)
    @Column(name = "record_items_list", length = 50, nullable = false)
    private String recordItemsList;

    @Size(max = 100)
    @Column(name = "record_items_other", length = 100)
    private String recordItemsOther;

    @Column(name = "remark")
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

    public String getEngCaseId() {
        return this.engCaseId;
    }

    public EngCase engCaseId(String engCaseId) {
        this.setEngCaseId(engCaseId);
        return this;
    }

    public void setEngCaseId(String engCaseId) {
        this.engCaseId = engCaseId;
    }

    public String getCaseNo() {
        return this.caseNo;
    }

    public EngCase caseNo(String caseNo) {
        this.setCaseNo(caseNo);
        return this;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getTenderNo() {
        return this.tenderNo;
    }

    public EngCase tenderNo(String tenderNo) {
        this.setTenderNo(tenderNo);
        return this;
    }

    public void setTenderNo(String tenderNo) {
        this.tenderNo = tenderNo;
    }

    public String getAwardNo() {
        return this.awardNo;
    }

    public EngCase awardNo(String awardNo) {
        this.setAwardNo(awardNo);
        return this;
    }

    public void setAwardNo(String awardNo) {
        this.awardNo = awardNo;
    }

    public String getCaseType() {
        return this.caseType;
    }

    public EngCase caseType(String caseType) {
        this.setCaseType(caseType);
        return this;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseName() {
        return this.caseName;
    }

    public EngCase caseName(String caseName) {
        this.setCaseName(caseName);
        return this;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getOrgId() {
        return this.orgId;
    }

    public EngCase orgId(String orgId) {
        this.setOrgId(orgId);
        return this;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public EngCase orgName(String orgName) {
        this.setOrgName(orgName);
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getExecOrgId() {
        return this.execOrgId;
    }

    public EngCase execOrgId(String execOrgId) {
        this.setExecOrgId(execOrgId);
        return this;
    }

    public void setExecOrgId(String execOrgId) {
        this.execOrgId = execOrgId;
    }

    public String getExecOrgName() {
        return this.execOrgName;
    }

    public EngCase execOrgName(String execOrgName) {
        this.setExecOrgName(execOrgName);
        return this;
    }

    public void setExecOrgName(String execOrgName) {
        this.execOrgName = execOrgName;
    }

    public String getContactPerson() {
        return this.contactPerson;
    }

    public EngCase contactPerson(String contactPerson) {
        this.setContactPerson(contactPerson);
        return this;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactTel() {
        return this.contactTel;
    }

    public EngCase contactTel(String contactTel) {
        this.setContactTel(contactTel);
        return this;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getEntrustItem() {
        return this.entrustItem;
    }

    public EngCase entrustItem(String entrustItem) {
        this.setEntrustItem(entrustItem);
        return this;
    }

    public void setEntrustItem(String entrustItem) {
        this.entrustItem = entrustItem;
    }

    public LocalDate getTenderDate() {
        return this.tenderDate;
    }

    public EngCase tenderDate(LocalDate tenderDate) {
        this.setTenderDate(tenderDate);
        return this;
    }

    public void setTenderDate(LocalDate tenderDate) {
        this.tenderDate = tenderDate;
    }

    public BigDecimal getTenderPrice() {
        return this.tenderPrice;
    }

    public EngCase tenderPrice(BigDecimal tenderPrice) {
        this.setTenderPrice(tenderPrice);
        return this;
    }

    public void setTenderPrice(BigDecimal tenderPrice) {
        this.tenderPrice = tenderPrice;
    }

    public LocalDate getExecDateStart() {
        return this.execDateStart;
    }

    public EngCase execDateStart(LocalDate execDateStart) {
        this.setExecDateStart(execDateStart);
        return this;
    }

    public void setExecDateStart(LocalDate execDateStart) {
        this.execDateStart = execDateStart;
    }

    public LocalDate getExecDateEnd() {
        return this.execDateEnd;
    }

    public EngCase execDateEnd(LocalDate execDateEnd) {
        this.setExecDateEnd(execDateEnd);
        return this;
    }

    public void setExecDateEnd(LocalDate execDateEnd) {
        this.execDateEnd = execDateEnd;
    }

    public String getProjectType() {
        return this.projectType;
    }

    public EngCase projectType(String projectType) {
        this.setProjectType(projectType);
        return this;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getRecordRangeList() {
        return this.recordRangeList;
    }

    public EngCase recordRangeList(String recordRangeList) {
        this.setRecordRangeList(recordRangeList);
        return this;
    }

    public void setRecordRangeList(String recordRangeList) {
        this.recordRangeList = recordRangeList;
    }

    public String getRecordRangeOther() {
        return this.recordRangeOther;
    }

    public EngCase recordRangeOther(String recordRangeOther) {
        this.setRecordRangeOther(recordRangeOther);
        return this;
    }

    public void setRecordRangeOther(String recordRangeOther) {
        this.recordRangeOther = recordRangeOther;
    }

    public String getRecordItemsList() {
        return this.recordItemsList;
    }

    public EngCase recordItemsList(String recordItemsList) {
        this.setRecordItemsList(recordItemsList);
        return this;
    }

    public void setRecordItemsList(String recordItemsList) {
        this.recordItemsList = recordItemsList;
    }

    public String getRecordItemsOther() {
        return this.recordItemsOther;
    }

    public EngCase recordItemsOther(String recordItemsOther) {
        this.setRecordItemsOther(recordItemsOther);
        return this;
    }

    public void setRecordItemsOther(String recordItemsOther) {
        this.recordItemsOther = recordItemsOther;
    }

    public String getRemark() {
        return this.remark;
    }

    public EngCase remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EngCase updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EngCase updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EngCase createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EngCase createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public EngCase transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return this.transferTime;
    }

    public EngCase transferTime(Instant transferTime) {
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
        if (!(o instanceof EngCase)) {
            return false;
        }
        return engCaseId != null && engCaseId.equals(((EngCase) o).engCaseId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngCase{" +
            "engCaseId=" + getEngCaseId() +
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
