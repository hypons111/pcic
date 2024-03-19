package gov.pcc.pwc.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A RecWorkItem.
 */
@Entity
@Table(name = "rec_work_item")
public class RecWorkItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * type: VARCHAR(22)
     */
    @NotNull
    @Size(max = 22)
    @Column(name = "area_no", length = 22, nullable = false)
    private String areaNo;

    /**
     * type: VARCHAR(22)
     */
    @NotNull
    @Size(max = 22)
    @Id
    @Column(name = "work_item_no", length = 22, nullable = false)
    private String workItemNo;

    /**
     * type: NVARCHAR(2000)
     */
    @Column(name = "const_item_name")
    private String constItemName;

    /**
     * type: NVARCHAR(2000)
     */
    @Column(name = "const_model_name")
    private String constModelName;

    /**
     * type: NVARCHAR(50)
     */
    @Column(name = "work_item_height")
    private String workItemHeight;

    /**
     * type: NVARCHAR(50)
     */
    @Column(name = "work_item_width")
    private String workItemWidth;

    /**
     * type: NVARCHAR(50)
     */
    @Column(name = "work_item_unit")
    private String workItemUnit;

    /**
     * type: decimal(12,0)
     */
    @Schema(description = "type: decimal(12,0)")
    @Column(name = "work_item_price", precision = 12, scale = 0)
    private BigDecimal workItemPrice;

    /**
     * type: decimal(12,0)
     */
    @Schema(description = "type: decimal(12,0)")
    @Column(name = "work_item_qty", precision = 12, scale = 0)
    private BigDecimal workItemQty;

    /**
     * type: decimal(12,0)
     */
    @Schema(description = "type: decimal(12,0)")
    @Column(name = "work_item_amount", precision = 12, scale = 0)
    private BigDecimal workItemAmount;

    /**
     * type: NVARCHAR(4000)
     */
    @Column(name = "remark")
    private String remark;

    /**
     * type: VARCHAR(1)
     */
    @NotNull
    @Column(name = "work_item_status", nullable = false)
    private String workItemStatus;

    /**
     * type: VARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    /**
     * type: NVARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getAreaNo() {
        return areaNo;
    }

    public RecWorkItem areaNo(String areaNo) {
        this.setAreaNo(areaNo);
        return this;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    public String getWorkItemNo() {
        return workItemNo;
    }

    public RecWorkItem workItemNo(String workItemNo) {
        this.setWorkItemNo(workItemNo);
        return this;
    }

    public void setWorkItemNo(String workItemNo) {
        this.workItemNo = workItemNo;
    }

    public String getConstItemName() {
        return constItemName;
    }

    public RecWorkItem constItemName(String constItemName) {
        this.setConstItemName(constItemName);
        return this;
    }

    public void setConstItemName(String constItemName) {
        this.constItemName = constItemName;
    }

    public String getConstModelName() {
        return constModelName;
    }

    public RecWorkItem constModelName(String constModelName) {
        this.setConstModelName(constModelName);
        return this;
    }

    public void setConstModelName(String constModelName) {
        this.constModelName = constModelName;
    }

    public String getWorkItemHeight() {
        return workItemHeight;
    }

    public RecWorkItem workItemHeight(String workItemHeight) {
        this.setWorkItemHeight(workItemHeight);
        return this;
    }

    public void setWorkItemHeight(String workItemHeight) {
        this.workItemHeight = workItemHeight;
    }

    public String getWorkItemWidth() {
        return workItemWidth;
    }

    public RecWorkItem workItemWidth(String workItemWidth) {
        this.setWorkItemWidth(workItemWidth);
        return this;
    }

    public void setWorkItemWidth(String workItemWidth) {
        this.workItemWidth = workItemWidth;
    }

    public String getWorkItemUnit() {
        return workItemUnit;
    }

    public RecWorkItem workItemUnit(String workItemUnit) {
        this.setWorkItemUnit(workItemUnit);
        return this;
    }

    public void setWorkItemUnit(String workItemUnit) {
        this.workItemUnit = workItemUnit;
    }

    public BigDecimal getWorkItemPrice() {
        return workItemPrice;
    }

    public RecWorkItem workItemPrice(BigDecimal workItemPrice) {
        this.setWorkItemPrice(workItemPrice);
        return this;
    }

    public void setWorkItemPrice(BigDecimal workItemPrice) {
        this.workItemPrice = workItemPrice;
    }

    public BigDecimal getWorkItemQty() {
        return workItemQty;
    }

    public RecWorkItem workItemQty(BigDecimal workItemQty) {
        this.setWorkItemQty(workItemQty);
        return this;
    }

    public void setWorkItemQty(BigDecimal workItemQty) {
        this.workItemQty = workItemQty;
    }

    public BigDecimal getWorkItemAmount() {
        return workItemAmount;
    }

    public RecWorkItem workItemAmount(BigDecimal workItemAmount) {
        this.setWorkItemAmount(workItemAmount);
        return this;
    }

    public void setWorkItemAmount(BigDecimal workItemAmount) {
        this.workItemAmount = workItemAmount;
    }

    public String getRemark() {
        return remark;
    }

    public RecWorkItem remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWorkItemStatus() {
        return workItemStatus;
    }

    public RecWorkItem workItemStatus(String workItemStatus) {
        this.setWorkItemStatus(workItemStatus);
        return this;
    }

    public void setWorkItemStatus(String workItemStatus) {
        this.workItemStatus = workItemStatus;
    }

    public String getCreateUser() {
        return createUser;
    }

    public RecWorkItem createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public RecWorkItem createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public RecWorkItem updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public RecWorkItem updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecWorkItem)) {
            return false;
        }
        return workItemNo != null && workItemNo.equals(((RecWorkItem) o).workItemNo);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RecArea{" +
                "areaNo=" + getAreaNo() +
                ", workItemNo='" + getWorkItemNo() + "'" +
                ", constItemName='" + getConstItemName() + "'" +
                ", constModelName='" + getConstModelName() + "'" +
                ", workItemHeight='" + getWorkItemHeight() + "'" +
                ", workItemWidth='" + getWorkItemWidth() + "'" +
                ", workItemUnit='" + getWorkItemUnit() + "'" +
                ", workItemPrice='" + getWorkItemPrice() + "'" +
                ", workItemQty='" + getWorkItemQty() + "'" +
                ", workItemAmount='" + getWorkItemAmount() + "'" +
                ", remark='" + getRemark() + "'" +
                ", workItemStatus='" + getWorkItemStatus() + "'" +
                ", createUser='" + getCreateUser() + "'" +
                ", createTime='" + getCreateTime() + "'" +
                ", updateUser='" + getUpdateUser() + "'" +
                ", updateTime='" + getUpdateTime() + "'" +
                "}";
    }
}
