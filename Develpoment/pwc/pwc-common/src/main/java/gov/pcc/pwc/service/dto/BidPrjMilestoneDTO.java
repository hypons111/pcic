package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjMilestonePrimaryKey;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjMilestone} entity.
 */
public class BidPrjMilestoneDTO implements Serializable {


    @Size(max = 21)
    private String wkut;

    @Size(max = 40)
    private String prjno;

    private LocalDate applyDate;

    @Size(max = 4)
    private String milestoneNo;

    @Size(max = 200)
    private String milestoneName;

    @Size(max = 2)
    private String milestoneType;

    @Size(max = 1)
    private String milestoneStatus;

    private LocalDate estimateDate;

    private LocalDate actualDate;

    @Size(max = 2000)
    private String remark;

    private Instant createDate;

    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    public BidPrjMilestonePrimaryKey getId() {
        return new BidPrjMilestonePrimaryKey(this.wkut,this.prjno,this.milestoneNo);
    }

    public void setId(BidPrjMilestonePrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.milestoneNo = id.getMilestoneNo();
    }

    public String getWkut() {
        return wkut;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return prjno;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public String getMilestoneNo() {
        return milestoneNo;
    }

    public void setMilestoneNo(String milestoneNo) {
        this.milestoneNo = milestoneNo;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public String getMilestoneType() {
        return milestoneType;
    }

    public void setMilestoneType(String milestoneType) {
        this.milestoneType = milestoneType;
    }

    public String getMilestoneStatus() {
        return milestoneStatus;
    }

    public void setMilestoneStatus(String milestoneStatus) {
        this.milestoneStatus = milestoneStatus;
    }

    public LocalDate getEstimateDate() {
        return estimateDate;
    }

    public void setEstimateDate(LocalDate estimateDate) {
        this.estimateDate = estimateDate;
    }

    public LocalDate getActualDate() {
        return actualDate;
    }

    public void setActualDate(LocalDate actualDate) {
        this.actualDate = actualDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjMilestoneDTO)) {
            return false;
        }

        BidPrjMilestoneDTO that = (BidPrjMilestoneDTO) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && milestoneNo.equals(that.milestoneNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut,prjno,milestoneNo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjMilestoneDTO{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", applyDate='" + getApplyDate() + "'" +
            ", milestoneNo='" + getMilestoneNo() + "'" +
            ", milestoneName='" + getMilestoneName() + "'" +
            ", milestoneType='" + getMilestoneType() + "'" +
            ", milestoneStatus='" + getMilestoneStatus() + "'" +
            ", estimateDate='" + getEstimateDate() + "'" +
            ", actualDate='" + getActualDate() + "'" +
            ", remark='" + getRemark() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
