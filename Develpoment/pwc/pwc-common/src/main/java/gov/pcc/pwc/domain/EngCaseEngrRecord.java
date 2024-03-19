package gov.pcc.pwc.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "eng_case_engr_record")
@IdClass(EngCaseEngrRecordPK.class)
public class EngCaseEngrRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "eng_case_id", length = 20, nullable = false)
    private String engCaseId;

    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "engr_id", length = 20, nullable = false)
    private String engrId;

    @NotNull
    @Id
    @Column(name = "item_no")
    private int itemNo;

    @Column(name = "expect_date")
    private LocalDate expectDate;

    @Column(name = "real_time")
    private Instant realTime;

    @Column(name = "record_content")
    private String recordContent;

    @Column(name = "record_comment")
    private String recordComment;

    @Column(name = "sign_date")
    private Instant signDate;

    @NotNull
    @Column(name = "no_sign_item")
    private int noSignItem;

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
        EngCaseEngrRecord that = (EngCaseEngrRecord) o;
        return itemNo == that.itemNo && noSignItem == that.noSignItem && Objects.equals(engCaseId, that.engCaseId) && Objects.equals(engrId, that.engrId) && Objects.equals(expectDate, that.expectDate) && Objects.equals(realTime, that.realTime) && Objects.equals(recordContent, that.recordContent) && Objects.equals(recordComment, that.recordComment) && Objects.equals(signDate, that.signDate) && Objects.equals(updateTime, that.updateTime) && Objects.equals(updateUser, that.updateUser) && Objects.equals(createTime, that.createTime) && Objects.equals(createUser, that.createUser) && Objects.equals(transferFrom, that.transferFrom) && Objects.equals(transferTime, that.transferTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engCaseId, engrId, itemNo, expectDate, realTime, recordContent, recordComment, signDate, noSignItem, updateTime, updateUser, createTime, createUser, transferFrom, transferTime);
    }

    @Override
    public String toString() {
        return "EngCaseEngrRecord{" +
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
