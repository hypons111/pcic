package gov.pcc.pwc.service.dto;

import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Objects;

//For查詢時使用DTO
public class RecTyphoonQueryDTO {

    @Size(max = 36)
    private String typhoonNo;

    @Size(max = 30)
    private String typhoonName;

    private Instant applyStartTime;

    private Instant applyEndTime;

    private Boolean isLocalOnly;

    private Instant finishDate1000;

    private Instant finishDate5000;

    private Instant bidProjectImportDate;

    private Instant centerApprovedDate;

    @Size(max = 2000)
    private String centerApprovedProgress;

    private Boolean isEditSubmitData;

    private Boolean isDenyBudgetChange;

    @Size(max = 20)
    private String createUser;

    private Instant createTime;

    @Size(max = 20)
    private String updateUser;

    private Instant updateTime;

    private int rowNum;

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


    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecTyphoonQueryDTO)) {
            return false;
        }

        RecTyphoonQueryDTO recTyphoonQueryDTO = (RecTyphoonQueryDTO) o;
        if (this.typhoonNo == null) {
            return false;
        }
        return Objects.equals(this.typhoonNo, recTyphoonQueryDTO.typhoonNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.typhoonNo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RecTyphoonQueryDTO{" +
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
                ", rowNum=" + getRowNum() +
                "}";
    }
}
