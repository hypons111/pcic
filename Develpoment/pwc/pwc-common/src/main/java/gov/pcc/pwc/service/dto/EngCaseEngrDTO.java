package gov.pcc.pwc.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

public class EngCaseEngrDTO {

    @NotNull
    @Size(max = 20)
    private String engCaseId;

    @NotNull
    private int itemNo;

    @Size(max = 20)
    private String compId;

    @Size(max = 20)
    private String engrId;

    @Size(max = 20)
    private String engrName;

    @Size(max = 50)
    private String engrNameRome;

    @NotNull
    private LocalDate execDateStart;

    @NotNull
    private LocalDate execDateEnd;

    @Size(max = 300)
    private String engEngrCertificateIdList;

    @Size(max = 100)
    private String recordRangeList;

    @Size(max = 100)
    private String recordRangeOther;

    @Size(max = 50)
    private String recordItemsList;

    @Size(max = 100)
    private String recordItemsOther;

    @Size(max = 30)
    private String docNo;

    private LocalDate closeDate;

    private String remark;

    @Size(max = 1)
    private String status;

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

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getEngrId() {
        return engrId;
    }

    public void setEngrId(String engrId) {
        this.engrId = engrId;
    }

    public String getEngrName() {
        return engrName;
    }

    public void setEngrName(String engrName) {
        this.engrName = engrName;
    }

    public String getEngrNameRome() {
        return engrNameRome;
    }

    public void setEngrNameRome(String engrNameRome) {
        this.engrNameRome = engrNameRome;
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

    public String getEngEngrCertificateIdList() {
        return engEngrCertificateIdList;
    }

    public void setEngEngrCertificateIdList(String engEngrCertificateIdList) {
        this.engEngrCertificateIdList = engEngrCertificateIdList;
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

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngCaseEngrDTO that = (EngCaseEngrDTO) o;
        return itemNo == that.itemNo && Objects.equals(engCaseId, that.engCaseId) && Objects.equals(compId, that.compId) && Objects.equals(engrId, that.engrId) && Objects.equals(engrName, that.engrName) && Objects.equals(engrNameRome, that.engrNameRome) && Objects.equals(execDateStart, that.execDateStart) && Objects.equals(execDateEnd, that.execDateEnd) && Objects.equals(engEngrCertificateIdList, that.engEngrCertificateIdList) && Objects.equals(recordRangeList, that.recordRangeList) && Objects.equals(recordRangeOther, that.recordRangeOther) && Objects.equals(recordItemsList, that.recordItemsList) && Objects.equals(recordItemsOther, that.recordItemsOther) && Objects.equals(docNo, that.docNo) && Objects.equals(closeDate, that.closeDate) && Objects.equals(remark, that.remark) && Objects.equals(status, that.status) && Objects.equals(updateTime, that.updateTime) && Objects.equals(updateUser, that.updateUser) && Objects.equals(createTime, that.createTime) && Objects.equals(createUser, that.createUser) && Objects.equals(transferFrom, that.transferFrom) && Objects.equals(transferTime, that.transferTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engCaseId, itemNo, compId, engrId, engrName, engrNameRome, execDateStart, execDateEnd, engEngrCertificateIdList, recordRangeList, recordRangeOther, recordItemsList, recordItemsOther, docNo, closeDate, remark, status, updateTime, updateUser, createTime, createUser, transferFrom, transferTime);
    }

    @Override
    public String toString() {
        return "EngCaseEngrDto{" +
                "engCaseId='" + engCaseId + '\'' +
                ", itemNo=" + itemNo +
                ", compId='" + compId + '\'' +
                ", engrId='" + engrId + '\'' +
                ", engrName='" + engrName + '\'' +
                ", engrNameRome='" + engrNameRome + '\'' +
                ", execDateStart=" + execDateStart +
                ", execDateEnd=" + execDateEnd +
                ", engEngrCertificateIdList='" + engEngrCertificateIdList + '\'' +
                ", recordRangeList='" + recordRangeList + '\'' +
                ", recordRangeOther='" + recordRangeOther + '\'' +
                ", recordItemsList='" + recordItemsList + '\'' +
                ", recordItemsOther='" + recordItemsOther + '\'' +
                ", docNo='" + docNo + '\'' +
                ", closeDate=" + closeDate +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", transferFrom='" + transferFrom + '\'' +
                ", transferTime=" + transferTime +
                '}';
    }
}
