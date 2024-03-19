package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjMilestone.
 */
@IdClass(BidPrjMilestonePrimaryKey.class)
@Entity
@Table(name = "bid_prj_milestone")
public class BidPrjMilestone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Size(max = 21)
    @Column(name = "wkut", length = 21)
    private String wkut;

    @Id
    @Size(max = 40)
    @Column(name = "prjno", length = 40)
    private String prjno;

    @Column(name = "apply_date")
    private LocalDate applyDate;

    @Id
    @Size(max = 4)
    @Column(name = "milestone_no", length = 4)
    private String milestoneNo;

    @Size(max = 200)
    @Column(name = "milestone_name", length = 200)
    private String milestoneName;

    @Size(max = 2)
    @Column(name = "milestone_type", length = 2)
    private String milestoneType;

    @Size(max = 1)
    @Column(name = "milestone_status", length = 1)
    private String milestoneStatus;

    @Column(name = "estimate_date")
    private LocalDate estimateDate;

    @Column(name = "actual_date")
    private LocalDate actualDate;

    @Size(max = 2000)
    @Column(name = "remark", length = 2000)
    private String remark;

    @Column(name = "create_date")
    private Instant createDate;

    @Size(max = 20)
    @Column(name = "create_user", length = 20)
    private String createUser;

    @Column(name = "update_date")
    private Instant updateDate;

    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjMilestonePrimaryKey getId() {
        return new BidPrjMilestonePrimaryKey(wkut,prjno,milestoneNo);
    }

    public BidPrjMilestone id(BidPrjMilestonePrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjMilestonePrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.milestoneNo = id.getMilestoneNo();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjMilestone wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjMilestone prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public LocalDate getApplyDate() {
        return this.applyDate;
    }

    public BidPrjMilestone applyDate(LocalDate applyDate) {
        this.setApplyDate(applyDate);
        return this;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public String getMilestoneNo() {
        return this.milestoneNo;
    }

    public BidPrjMilestone milestoneNo(String milestoneNo) {
        this.setMilestoneNo(milestoneNo);
        return this;
    }

    public void setMilestoneNo(String milestoneNo) {
        this.milestoneNo = milestoneNo;
    }

    public String getMilestoneName() {
        return this.milestoneName;
    }

    public BidPrjMilestone milestoneName(String milestoneName) {
        this.setMilestoneName(milestoneName);
        return this;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public String getMilestoneType() {
        return this.milestoneType;
    }

    public BidPrjMilestone milestoneType(String milestoneType) {
        this.setMilestoneType(milestoneType);
        return this;
    }

    public void setMilestoneType(String milestoneType) {
        this.milestoneType = milestoneType;
    }

    public String getMilestoneStatus() {
        return this.milestoneStatus;
    }

    public BidPrjMilestone milestoneStatus(String milestoneStatus) {
        this.setMilestoneStatus(milestoneStatus);
        return this;
    }

    public void setMilestoneStatus(String milestoneStatus) {
        this.milestoneStatus = milestoneStatus;
    }

    public LocalDate getEstimateDate() {
        return this.estimateDate;
    }

    public BidPrjMilestone estimateDate(LocalDate estimateDate) {
        this.setEstimateDate(estimateDate);
        return this;
    }

    public void setEstimateDate(LocalDate estimateDate) {
        this.estimateDate = estimateDate;
    }

    public LocalDate getActualDate() {
        return this.actualDate;
    }

    public BidPrjMilestone actualDate(LocalDate actualDate) {
        this.setActualDate(actualDate);
        return this;
    }

    public void setActualDate(LocalDate actualDate) {
        this.actualDate = actualDate;
    }

    public String getRemark() {
        return this.remark;
    }

    public BidPrjMilestone remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjMilestone createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjMilestone createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjMilestone updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjMilestone updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjMilestone)) {
            return false;
        }
        BidPrjMilestone that = (BidPrjMilestone) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && milestoneNo.equals(that.milestoneNo);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return Objects.hash(wkut,prjno,milestoneNo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjMilestone{" +
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
