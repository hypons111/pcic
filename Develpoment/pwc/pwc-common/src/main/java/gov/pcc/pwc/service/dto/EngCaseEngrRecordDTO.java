package gov.pcc.pwc.service.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

public class EngCaseEngrRecordDTO {

    @NotNull
    @Size(max = 20)
    private String engCaseId;

    @NotNull
    @Size(max = 20)
    private String engrId;

    @NotNull
    @Id
    private int itemNo;

    private LocalDate expectDate;

    private Instant realTime;

    private String recordContent;

    private String recordComment;

    private Instant signDate;

    @NotNull
    private int noSignItem;

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

    public String getEngrId() {
        return engrId;
    }

    public void setEngrId(String engrId) {
        this.engrId = engrId;
    }

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public LocalDate getExpectDate() {
        return expectDate;
    }

    public void setExpectDate(LocalDate expectDate) {
        this.expectDate = expectDate;
    }

    public Instant getRealTime() {
        return realTime;
    }

    public void setRealTime(Instant realTime) {
        this.realTime = realTime;
    }

    public String getRecordContent() {
        return recordContent;
    }

    public void setRecordContent(String recordContent) {
        this.recordContent = recordContent;
    }

    public String getRecordComment() {
        return recordComment;
    }

    public void setRecordComment(String recordComment) {
        this.recordComment = recordComment;
    }

    public Instant getSignDate() {
        return signDate;
    }

    public void setSignDate(Instant signDate) {
        this.signDate = signDate;
    }

    public int getNoSignItem() {
        return noSignItem;
    }

    public void setNoSignItem(int noSignItem) {
        this.noSignItem = noSignItem;
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
        EngCaseEngrRecordDTO that = (EngCaseEngrRecordDTO) o;
        return itemNo == that.itemNo && noSignItem == that.noSignItem && Objects.equals(engCaseId, that.engCaseId) && Objects.equals(engrId, that.engrId) && Objects.equals(expectDate, that.expectDate) && Objects.equals(realTime, that.realTime) && Objects.equals(recordContent, that.recordContent) && Objects.equals(recordComment, that.recordComment) && Objects.equals(signDate, that.signDate) && Objects.equals(updateTime, that.updateTime) && Objects.equals(updateUser, that.updateUser) && Objects.equals(createTime, that.createTime) && Objects.equals(createUser, that.createUser) && Objects.equals(transferFrom, that.transferFrom) && Objects.equals(transferTime, that.transferTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engCaseId, engrId, itemNo, expectDate, realTime, recordContent, recordComment, signDate, noSignItem, updateTime, updateUser, createTime, createUser, transferFrom, transferTime);
    }

    @Override
    public String toString() {
        return "EngCaseEngrRecordDTO{" +
                "engCaseId='" + engCaseId + '\'' +
                ", engrId='" + engrId + '\'' +
                ", itemNo=" + itemNo +
                ", expectDate=" + expectDate +
                ", realTime=" + realTime +
                ", recordContent='" + recordContent + '\'' +
                ", recordComment='" + recordComment + '\'' +
                ", signDate=" + signDate +
                ", noSignItem=" + noSignItem +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", transferFrom='" + transferFrom + '\'' +
                ", transferTime=" + transferTime +
                '}';
    }
}
