package gov.pcc.pwc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

/**
 * A RecTyphoon.
 */
@Entity
@Table(name = "rec_typhoon")
public class RecTyphoon implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * type: VARCHAR(36)
     */
    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "typhoon_no", length = 36, nullable = false)
    private String typhoonNo;

    /**
     * type: NVARCHAR(30)
     */
    @Size(max = 30)
    @Column(name = "typhoon_name", length = 30)
    private String typhoonName;

    /**
     * type: DATETIME
     */
    @NotNull
    @Column(name = "apply_start_time", nullable = false)
    private Instant applyStartTime;

    /**
     * type: DATETIME
     */
    @Column(name = "apply_end_time")
    private Instant applyEndTime;

    /**
     * type: BIT
     */
    @NotNull
    @Column(name = "is_local_only", nullable = false)
    private Boolean isLocalOnly;

    /**
     * type: DATETIME
     */
    @NotNull
    @Column(name = "finish_date1000", nullable = false)
    private Instant finishDate1000;

    /**
     * type: DATETIME
     */
    @NotNull
    @Column(name = "finish_date5000", nullable = false)
    private Instant finishDate5000;

    /**
     * type: DATETIME
     */
    @Column(name = "bid_project_import_date")
    private Instant bidProjectImportDate;

    /**
     * type: DATETIME
     */
    @Column(name = "center_approved_date")
    private Instant centerApprovedDate;

    /**
     * type: VARCHAR(2000)
     */
    @Size(max = 2000)
    @Column(name = "center_approved_progress", length = 2000)
    private String centerApprovedProgress;

    /**
     * type: BIT
     */
    @NotNull
    @Column(name = "is_edit_submit_data", nullable = false)
    private Boolean isEditSubmitData;

    /**
     * type: BIT
     */
    @NotNull
    @Column(name = "is_deny_budget_change", nullable = false)
    private Boolean isDenyBudgetChange;

    /**
     * type: NVARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    /**
     * type: NVARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getTyphoonNo() {
        return this.typhoonNo;
    }

    public RecTyphoon typhoonNo(String typhoonNo) {
        this.setTyphoonNo(typhoonNo);
        return this;
    }

    public void setTyphoonNo(String typhoonNo) {
        this.typhoonNo = typhoonNo;
    }

    public String getTyphoonName() {
        return this.typhoonName;
    }

    public RecTyphoon typhoonName(String typhoonName) {
        this.setTyphoonName(typhoonName);
        return this;
    }

    public void setTyphoonName(String typhoonName) {
        this.typhoonName = typhoonName;
    }

    public Instant getApplyStartTime() {
        return this.applyStartTime;
    }

    public RecTyphoon applyStartTime(Instant applyStartTime) {
        this.setApplyStartTime(applyStartTime);
        return this;
    }

    public void setApplyStartTime(Instant applyStartTime) {
        this.applyStartTime = applyStartTime;
    }

    public Instant getApplyEndTime() {
        return this.applyEndTime;
    }

    public RecTyphoon applyEndTime(Instant applyEndTime) {
        this.setApplyEndTime(applyEndTime);
        return this;
    }

    public void setApplyEndTime(Instant applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public Boolean getIsLocalOnly() {
        return this.isLocalOnly;
    }

    public RecTyphoon isLocalOnly(Boolean isLocalOnly) {
        this.setIsLocalOnly(isLocalOnly);
        return this;
    }

    public void setIsLocalOnly(Boolean isLocalOnly) {
        this.isLocalOnly = isLocalOnly;
    }

    public Instant getFinishDate1000() {
        return this.finishDate1000;
    }

    public RecTyphoon finishDate1000(Instant finishDate1000) {
        this.setFinishDate1000(finishDate1000);
        return this;
    }

    public void setFinishDate1000(Instant finishDate1000) {
        this.finishDate1000 = finishDate1000;
    }

    public Instant getFinishDate5000() {
        return this.finishDate5000;
    }

    public RecTyphoon finishDate5000(Instant finishDate5000) {
        this.setFinishDate5000(finishDate5000);
        return this;
    }

    public void setFinishDate5000(Instant finishDate5000) {
        this.finishDate5000 = finishDate5000;
    }

    public Instant getBidProjectImportDate() {
        return this.bidProjectImportDate;
    }

    public RecTyphoon bidProjectImportDate(Instant bidProjectImportDate) {
        this.setBidProjectImportDate(bidProjectImportDate);
        return this;
    }

    public void setBidProjectImportDate(Instant bidProjectImportDate) {
        this.bidProjectImportDate = bidProjectImportDate;
    }

    public Instant getCenterApprovedDate() {
        return this.centerApprovedDate;
    }

    public RecTyphoon centerApprovedDate(Instant centerApprovedDate) {
        this.setCenterApprovedDate(centerApprovedDate);
        return this;
    }

    public void setCenterApprovedDate(Instant centerApprovedDate) {
        this.centerApprovedDate = centerApprovedDate;
    }

    public String getCenterApprovedProgress() {
        return this.centerApprovedProgress;
    }

    public RecTyphoon centerApprovedProgress(String centerApprovedProgress) {
        this.setCenterApprovedProgress(centerApprovedProgress);
        return this;
    }

    public void setCenterApprovedProgress(String centerApprovedProgress) {
        this.centerApprovedProgress = centerApprovedProgress;
    }

    public Boolean getIsEditSubmitData() {
        return this.isEditSubmitData;
    }

    public RecTyphoon isEditSubmitData(Boolean isEditSubmitData) {
        this.setIsEditSubmitData(isEditSubmitData);
        return this;
    }

    public void setIsEditSubmitData(Boolean isEditSubmitData) {
        this.isEditSubmitData = isEditSubmitData;
    }

    public Boolean getIsDenyBudgetChange() {
        return this.isDenyBudgetChange;
    }

    public RecTyphoon isDenyBudgetChange(Boolean isDenyBudgetChange) {
        this.setIsDenyBudgetChange(isDenyBudgetChange);
        return this;
    }

    public void setIsDenyBudgetChange(Boolean isDenyBudgetChange) {
        this.isDenyBudgetChange = isDenyBudgetChange;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public RecTyphoon createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public RecTyphoon createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public RecTyphoon updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public RecTyphoon updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecTyphoon)) {
            return false;
        }
        return typhoonNo != null && typhoonNo.equals(((RecTyphoon) o).typhoonNo);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RecTyphoon{" +
            "typhoonNo=" + getTyphoonNo() +
            ", typhoonName='" + getTyphoonName() + "'" +
            ", applyStartTime='" + getApplyStartTime() + "'" +
            ", applyEndTime='" + getApplyEndTime() + "'" +
            ", isLocalOnly='" + getIsLocalOnly() + "'" +
            ", finishDate1000='" + getFinishDate1000() + "'" +
            ", finishDate5000='" + getFinishDate5000() + "'" +
            ", bidProjectImportDate='" + getBidProjectImportDate() + "'" +
            ", centerApprovedDate='" + getCenterApprovedDate() + "'" +
            ", centerApprovedProgress='" + getCenterApprovedProgress() + "'" +
            ", isEditSubmitData='" + getIsEditSubmitData() + "'" +
            ", isDenyBudgetChange='" + getIsDenyBudgetChange() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
