package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.RecWorkItem} entity.
 */
public class RecWorkItemDTO implements Serializable {

    /**
     * type: VARCHAR(22)
     */
    @NotNull
    @Size(max = 22)
    @Schema(description = "type: VARCHAR(22)", nullable = false)
    private String areaNo;

    /**
     * type: VARCHAR(22)
     */
    @NotNull
    @Size(max = 22)
    @Id
    @Schema(description = "type: VARCHAR(22)", nullable = false)
    private String workItemNo;

    /**
     * type: NVARCHAR(2000)
     */
    @Schema(description = "type: NVARCHAR(2000)")
    private String constItemName;

    /**
     * type: NVARCHAR(2000)
     */
    @Schema(description = "type: NVARCHAR(2000)")
    private String constModelName;

    /**
     * type: NVARCHAR(50)
     */
    @Schema(description = "type: NVARCHAR(50)")
    private String workItemHeight;

    /**
     * type: NVARCHAR(50)
     */
    @Schema(description = "type: NVARCHAR(50)")
    private String workItemWidth;

    /**
     * type: NVARCHAR(50)
     */
    @Schema(description = "type: NVARCHAR(50)")
    private String workItemUnit;

    /**
     * type: decimal(12,0)
     */
    @Schema(description = "type: decimal(12,0)")
    private BigDecimal workItemPrice;

    /**
     * type: decimal(12,0)
     */
    @Schema(description = "type: decimal(12,0)")
    private BigDecimal workItemQty;

    /**
     * type: decimal(12,0)
     */
    @Schema(description = "type: decimal(12,0)")
    private BigDecimal workItemAmount;

    /**
     * type: NVARCHAR(4000)
     */
    @Schema(description = "type: NVARCHAR(4000)")
    private String remark;

    /**
     * type: VARCHAR(1)
     */
    @NotNull
    @Schema(description = "type: VARCHAR(1)")
    private String workItemStatus;

    /**
     * type: VARCHAR(20)
     */
    @NotNull
    @Schema(description = "type: VARCHAR(20)")
    private String createUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Schema(description = "type: DATETIME")
    private Instant createTime;

    /**
     * type: NVARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)")
    private String updateUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Schema(description = "type: DATETIME")
    private Instant updateTime;

    // getter & setter

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    public String getWorkItemNo() {
        return workItemNo;
    }

    public void setWorkItemNo(String workItemNo) {
        this.workItemNo = workItemNo;
    }

    public String getConstItemName() {
        return constItemName;
    }

    public void setConstItemName(String constItemName) {
        this.constItemName = constItemName;
    }

    public String getConstModelName() {
        return constModelName;
    }

    public void setConstModelName(String constModelName) {
        this.constModelName = constModelName;
    }

    public String getWorkItemHeight() {
        return workItemHeight;
    }

    public void setWorkItemHeight(String workItemHeight) {
        this.workItemHeight = workItemHeight;
    }

    public String getWorkItemWidth() {
        return workItemWidth;
    }

    public void setWorkItemWidth(String workItemWidth) {
        this.workItemWidth = workItemWidth;
    }

    public String getWorkItemUnit() {
        return workItemUnit;
    }

    public void setWorkItemUnit(String workItemUnit) {
        this.workItemUnit = workItemUnit;
    }

    public BigDecimal getWorkItemPrice() {
        return workItemPrice;
    }

    public void setWorkItemPrice(BigDecimal workItemPrice) {
        this.workItemPrice = workItemPrice;
    }

    public BigDecimal getWorkItemQty() {
        return workItemQty;
    }

    public void setWorkItemQty(BigDecimal workItemQty) {
        this.workItemQty = workItemQty;
    }

    public BigDecimal getWorkItemAmount() {
        return workItemAmount;
    }

    public void setWorkItemAmount(BigDecimal workItemAmount) {
        this.workItemAmount = workItemAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWorkItemStatus() {
        return workItemStatus;
    }

    public void setWorkItemStatus(String workItemStatus) {
        this.workItemStatus = workItemStatus;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecWorkItemDTO)) {
            return false;
        }

        RecWorkItemDTO recWorkItemDTO = (RecWorkItemDTO) o;
        if (this.workItemNo == null) {
            return false;
        }
        return Objects.equals(this.workItemNo, recWorkItemDTO.workItemNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.workItemNo);
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
