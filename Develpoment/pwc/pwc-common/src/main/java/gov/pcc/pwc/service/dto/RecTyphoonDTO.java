package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.RecTyphoon} entity.
 */
public class RecTyphoonDTO implements Serializable {

    /**
     * type: VARCHAR(36)
     */
    @NotNull
    @Size(max = 36)
    @Schema(description = "type: VARCHAR(36)", required = true)
    private String typhoonNo;

    /**
     * type: NVARCHAR(30)
     */
    @Size(max = 30)
    @Schema(description = "type: NVARCHAR(30)")
    private String typhoonName;

    /**
     * type: DATETIME
     */
    @NotNull
    @Schema(description = "type: DATETIME", required = true)
    private Instant applyStartTime;

    /**
     * type: DATETIME
     */
    @Schema(description = "type: DATETIME")
    private Instant applyEndTime;

    /**
     * type: BIT
     */
    @NotNull
    @Schema(description = "type: BIT", required = true)
    private Boolean isLocalOnly;

    /**
     * type: DATETIME
     */
    @NotNull
    @Schema(description = "type: DATETIME", required = true)
    private Instant finishDate1000;

    /**
     * type: DATETIME
     */
    @NotNull
    @Schema(description = "type: DATETIME", required = true)
    private Instant finishDate5000;

    /**
     * type: DATETIME
     */
    @Schema(description = "type: DATETIME")
    private Instant bidProjectImportDate;

    /**
     * type: DATETIME
     */
    @Schema(description = "type: DATETIME")
    private Instant centerApprovedDate;

    /**
     * type: VARCHAR(2000)
     */
    @Size(max = 2000)
    @Schema(description = "type: VARCHAR(2000)")
    private String centerApprovedProgress;

    /**
     * type: BIT
     */
    @NotNull
    @Schema(description = "type: BIT", required = true)
    private Boolean isEditSubmitData;

    /**
     * type: BIT
     */
    @NotNull
    @Schema(description = "type: BIT", required = true)
    private Boolean isDenyBudgetChange;

    /**
     * type: NVARCHAR(20)
     */
//    @NotNull 暫時拿掉，待確認insert的方法
    @Size(max = 20)
    @Schema(description = "type: NVARCHAR(20)", required = true)
    private String createUser;

    /**
     * type: DATETIME
     */
//    @NotNull 暫時拿掉，待確認insert的方法
    @Schema(description = "type: DATETIME", required = true)
    private Instant createTime;

    /**
     * type: NVARCHAR(20)
     */
//    @NotNull 暫時拿掉，待確認insert的方法
    @Size(max = 20)
    @Schema(description = "type: NVARCHAR(20)", required = true)
    private String updateUser;

    /**
     * type: DATETIME
     */
//    @NotNull 暫時拿掉，待確認insert的方法
    @Schema(description = "type: DATETIME", required = true)
    private Instant updateTime;

    public String getTyphoonNo() {
        return typhoonNo;
    }

    public void setTyphoonNo(String typhoonNo) {
        this.typhoonNo = typhoonNo;
    }

    public String getTyphoonName() {
        return typhoonName;
    }

    public void setTyphoonName(String typhoonName) {
        this.typhoonName = typhoonName;
    }

    public Instant getApplyStartTime() {
        return applyStartTime;
    }

    public void setApplyStartTime(Instant applyStartTime) {
        this.applyStartTime = applyStartTime;
    }

    public Instant getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(Instant applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public Boolean getIsLocalOnly() {
        return isLocalOnly;
    }

    public void setIsLocalOnly(Boolean isLocalOnly) {
        this.isLocalOnly = isLocalOnly;
    }

    public Instant getFinishDate1000() {
        return finishDate1000;
    }

    public void setFinishDate1000(Instant finishDate1000) {
        this.finishDate1000 = finishDate1000;
    }

    public Instant getFinishDate5000() {
        return finishDate5000;
    }

    public void setFinishDate5000(Instant finishDate5000) {
        this.finishDate5000 = finishDate5000;
    }

    public Instant getBidProjectImportDate() {
        return bidProjectImportDate;
    }

    public void setBidProjectImportDate(Instant bidProjectImportDate) {
        this.bidProjectImportDate = bidProjectImportDate;
    }

    public Instant getCenterApprovedDate() {
        return centerApprovedDate;
    }

    public void setCenterApprovedDate(Instant centerApprovedDate) {
        this.centerApprovedDate = centerApprovedDate;
    }

    public String getCenterApprovedProgress() {
        return centerApprovedProgress;
    }

    public void setCenterApprovedProgress(String centerApprovedProgress) {
        this.centerApprovedProgress = centerApprovedProgress;
    }

    public Boolean getIsEditSubmitData() {
        return isEditSubmitData;
    }

    public void setIsEditSubmitData(Boolean isEditSubmitData) {
        this.isEditSubmitData = isEditSubmitData;
    }

    public Boolean getIsDenyBudgetChange() {
        return isDenyBudgetChange;
    }

    public void setIsDenyBudgetChange(Boolean isDenyBudgetChange) {
        this.isDenyBudgetChange = isDenyBudgetChange;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecTyphoonDTO)) {
            return false;
        }

        RecTyphoonDTO recTyphoonDTO = (RecTyphoonDTO) o;
        if (this.typhoonNo == null) {
            return false;
        }
        return Objects.equals(this.typhoonNo, recTyphoonDTO.typhoonNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.typhoonNo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RecTyphoonDTO{" +
            "typhoonNo='" + getTyphoonNo() + "'" +
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
