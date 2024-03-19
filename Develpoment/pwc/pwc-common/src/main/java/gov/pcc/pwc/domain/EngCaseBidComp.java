package gov.pcc.pwc.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "eng_case_bid_comp")
@IdClass(EngCaseBidCompPK.class)
public class EngCaseBidComp implements Serializable {

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
    @Column(name = "comp_idno")
    private String compIdno;

    @Size(max = 200)
    @Column(name = "comp_name")
    private String compName;

    @Size(max = 50)
    @Column(name = "comp_license_no")
    private String compLicenseNo;

    @Column(name = "exec_date_start")
    private LocalDate execDateStart;

    @Column(name = "exec_date_end")
    private LocalDate execDateEnd;

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

    public String getCompIdno() {
        return compIdno;
    }

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompLicenseNo() {
        return compLicenseNo;
    }

    public void setCompLicenseNo(String compLicenseNo) {
        this.compLicenseNo = compLicenseNo;
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

    // idea建的
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EngCaseBidComp)) {return false;}
        EngCaseBidComp that = (EngCaseBidComp) o;
        return itemNo == that.itemNo && Objects.equals(engCaseId, that.engCaseId) && Objects.equals(compId, that.compId) && Objects.equals(compIdno, that.compIdno) && Objects.equals(compName, that.compName) && Objects.equals(compLicenseNo, that.compLicenseNo) && Objects.equals(execDateStart, that.execDateStart) && Objects.equals(execDateEnd, that.execDateEnd) && Objects.equals(updateTime, that.updateTime) && Objects.equals(updateUser, that.updateUser) && Objects.equals(createTime, that.createTime) && Objects.equals(createUser, that.createUser) && Objects.equals(transferFrom, that.transferFrom) && Objects.equals(transferTime, that.transferTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engCaseId, itemNo, compId, compIdno, compName, compLicenseNo, execDateStart, execDateEnd, updateTime, updateUser, createTime, createUser, transferFrom, transferTime);
    }

    @Override
    public String toString() {
        return "EngCaseBidComp{" +
                "engCaseId='" + engCaseId + '\'' +
                ", itemNo=" + itemNo +
                ", compId='" + compId + '\'' +
                ", compIdno='" + compIdno + '\'' +
                ", compName='" + compName + '\'' +
                ", compLicenseNo='" + compLicenseNo + '\'' +
                ", execDateStart=" + execDateStart +
                ", execDateEnd=" + execDateEnd +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", transferFrom='" + transferFrom + '\'' +
                ", transferTime=" + transferTime +
                '}';
    }
}
