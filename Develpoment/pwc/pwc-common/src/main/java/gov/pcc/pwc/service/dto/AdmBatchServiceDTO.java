package gov.pcc.pwc.service.dto;

import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Objects;

public class AdmBatchServiceDTO {

    @Size(max = 20)
    private String batchServiceNo;

    @Size(max = 20)
    private String batchServiceName;

    @Size(max = 10)
    private String batchServiceCategory;

    @Size(max = 1)
    private String isRegular;

    @Size(max = 150)
    private String batchServiceProg;

    @Size(max = 100)
    private String batchServiceProgMethod;

    @Size(max = 1)
    private String batchServiceStatus;

    @Size(max = 1)
    private String batchExeStatus;

    @Size(max = 20)
    private String batchFrequency;

    @Size(max = 20)
    private String updateUser;

    private Instant updateTime;

    private int rowNum;

    public String getBatchServiceNo() {
        return batchServiceNo;
    }

    public void setBatchServiceNo(String batchServiceNo) {
        this.batchServiceNo = batchServiceNo;
    }

    public String getBatchServiceName() {
        return batchServiceName;
    }

    public void setBatchServiceName(String batchServiceName) {
        this.batchServiceName = batchServiceName;
    }

    public String getBatchServiceCategory() {
        return batchServiceCategory;
    }

    public void setBatchServiceCategory(String batchServiceCategory) {
        this.batchServiceCategory = batchServiceCategory;
    }

    public String getIsRegular() {
        return isRegular;
    }

    public void setIsRegular(String isRegular) {
        this.isRegular = isRegular;
    }

    public String getBatchServiceProg() {
        return batchServiceProg;
    }

    public void setBatchServiceProg(String batchServiceProg) {
        this.batchServiceProg = batchServiceProg;
    }

    public String getBatchServiceProgMethod() {
        return batchServiceProgMethod;
    }

    public void setBatchServiceProgMethod(String batchServiceProgMethod) {
        this.batchServiceProgMethod = batchServiceProgMethod;
    }

    public String getBatchServiceStatus() {
        return batchServiceStatus;
    }

    public void setBatchServiceStatus(String batchServiceStatus) {
        this.batchServiceStatus = batchServiceStatus;
    }

    public String getBatchExeStatus() {
        return batchExeStatus;
    }

    public void setBatchExeStatus(String batchExeStatus) {
        this.batchExeStatus = batchExeStatus;
    }

    public String getBatchFrequency() {
        return batchFrequency;
    }

    public void setBatchFrequency(String batchFrequency) {
        this.batchFrequency = batchFrequency;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmBatchServiceDTO that = (AdmBatchServiceDTO) o;
        return Objects.equals(batchServiceNo, that.batchServiceNo) && Objects.equals(batchServiceName, that.batchServiceName) && Objects.equals(batchServiceCategory, that.batchServiceCategory) && Objects.equals(batchServiceProg, that.batchServiceProg) && Objects.equals(batchServiceStatus, that.batchServiceStatus) && Objects.equals(batchExeStatus, that.batchExeStatus) && Objects.equals(batchFrequency, that.batchFrequency) && Objects.equals(updateUser, that.updateUser) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(batchServiceNo, batchServiceName, batchServiceCategory, isRegular, batchServiceProg, batchServiceStatus, batchExeStatus, batchFrequency, updateUser, updateTime, rowNum);
    }

    @Override
    public String toString() {
        return "AdmBatchServiceDTO{" +
                "batchServiceNo='" + batchServiceNo + '\'' +
                ", batchServiceName='" + batchServiceName + '\'' +
                ", batchServiceCategory='" + batchServiceCategory + '\'' +
                ", isRegular=" + isRegular +
                ", batchServiceProg='" + batchServiceProg + '\'' +
                ", batchServiceStatus='" + batchServiceStatus + '\'' +
                ", batchExeStatus='" + batchExeStatus + '\'' +
                ", batchFrequency='" + batchFrequency + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", rowNum=" + rowNum +
                '}';
    }
}
