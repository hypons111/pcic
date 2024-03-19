package gov.pcc.pwc.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "eng_case_engr")
@IdClass(EngCaseEngrPK.class)
public class EngCaseEngr implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "eng_case_id", length = 20, nullable = false)
    private String engCaseId;

    @NotNull
    @Id
    @Column(name = "item_no")
    private int itemNo;

    @Size(max = 20)
    @Column(name = "comp_id")
    private String compId;

    @Size(max = 20)
    @Column(name = "engr_id")
    private String engrId;

    @Size(max = 20)
    @Column(name = "engr_name")
    private String engrName;

    @Size(max = 50)
    @Column(name = "engr_name_rome")
    private String engrNameRome;

    @NotNull
    @Column(name = "exec_date_start")
    private LocalDate execDateStart;

    @NotNull
    @Column(name = "exec_date_end")
    private LocalDate execDateEnd;

    @Size(max = 300)
    @Column(name = "eng_engr_certificate_id_list")
    private String engEngrCertificateIdList;

    @Size(max = 100)
    @Column(name = "record_range_list")
    private String recordRangeList;

    @Size(max = 100)
    @Column(name = "record_range_other")
    private String recordRangeOther;

    @Size(max = 50)
    @Column(name = "record_items_list")
    private String recordItemsList;

    @Size(max = 100)
    @Column(name = "record_items_other")
    private String recordItemsOther;

    @Size(max = 30)
    @Column(name = "doc_no")
    private String docNo;

    @Column(name = "close_date")
    private LocalDate closeDate;

    @Column(name = "remark")
    private String remark;

    @Size(max = 1)
    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "update_time")
    private Instant updateTime;

    @NotNull
    @Size(max = 20)
    @Column(name = "update_user")
    private String updateUser;

    @NotNull
    @Column(name = "create_time")
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user")
    private String createUser;

    @Size(max = 100)
    @Column(name = "transfer_from")
    private String transferFrom;

    @Column(name = "transfer_time")
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
        EngCaseEngr that = (EngCaseEngr) o;
        return itemNo == that.itemNo && Objects.equals(engCaseId, that.engCaseId) && Objects.equals(compId, that.compId) && Objects.equals(engrId, that.engrId) && Objects.equals(engrName, that.engrName) && Objects.equals(engrNameRome, that.engrNameRome) && Objects.equals(execDateStart, that.execDateStart) && Objects.equals(execDateEnd, that.execDateEnd) && Objects.equals(engEngrCertificateIdList, that.engEngrCertificateIdList) && Objects.equals(recordRangeList, that.recordRangeList) && Objects.equals(recordRangeOther, that.recordRangeOther) && Objects.equals(recordItemsList, that.recordItemsList) && Objects.equals(recordItemsOther, that.recordItemsOther) && Objects.equals(docNo, that.docNo) && Objects.equals(closeDate, that.closeDate) && Objects.equals(remark, that.remark) && Objects.equals(status, that.status) && Objects.equals(updateTime, that.updateTime) && Objects.equals(updateUser, that.updateUser) && Objects.equals(createTime, that.createTime) && Objects.equals(createUser, that.createUser) && Objects.equals(transferFrom, that.transferFrom) && Objects.equals(transferTime, that.transferTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engCaseId, itemNo, compId, engrId, engrName, engrNameRome, execDateStart, execDateEnd, engEngrCertificateIdList, recordRangeList, recordRangeOther, recordItemsList, recordItemsOther, docNo, closeDate, remark, status, updateTime, updateUser, createTime, createUser, transferFrom, transferTime);
    }

    @Override
    public String toString() {
        return "EngCaseEngr{" +
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
