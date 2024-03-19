package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A PwbUnitApply.
 */
@Entity
@Table(name = "pwb_unit_apply")
public class PwbUnitApply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * type: BIGINT
     */
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * type: VARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "unit_id", length = 20, nullable = false)
    private String unitId;

    /**
     * type: DATETIME
     */
    @Column(name = "apply_time")
    private Instant applyTime;

    /**
     * type: DATETIME
     */
    @Column(name = "approve_time")
    private Instant approveTime;

    /**
     * type: VARCHAR(1)
     */
    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

    /**
     * type: VARCHAR(3)
     */
    @NotNull
    @Size(max = 3)
    @Column(name = "module_type", length = 3, nullable = false)
    private String moduleType;

    /**
     * type: VARCHAR(20)
     */
    @Size(max = 20)
    @Column(name = "business_type", length = 20)
    private String businessType;

    /**
     * type: VARCHAR(20)
     */
    @Size(max = 20)
    @Column(name = "approve_unit", length = 20)
    private String approveUnit;

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

    /**
     * type: NVARCHAR(20)
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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PwbUnitApply id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitId() {
        return this.unitId;
    }

    public PwbUnitApply unitId(String unitId) {
        this.setUnitId(unitId);
        return this;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public Instant getApplyTime() {
        return this.applyTime;
    }

    public PwbUnitApply applyTime(Instant applyTime) {
        this.setApplyTime(applyTime);
        return this;
    }

    public void setApplyTime(Instant applyTime) {
        this.applyTime = applyTime;
    }

    public Instant getApproveTime() {
        return this.approveTime;
    }

    public PwbUnitApply approveTime(Instant approveTime) {
        this.setApproveTime(approveTime);
        return this;
    }

    public void setApproveTime(Instant approveTime) {
        this.approveTime = approveTime;
    }

    public String getStatus() {
        return this.status;
    }

    public PwbUnitApply status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModuleType() {
        return this.moduleType;
    }

    public PwbUnitApply moduleType(String moduleType) {
        this.setModuleType(moduleType);
        return this;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public PwbUnitApply businessType(String businessType) {
        this.setBusinessType(businessType);
        return this;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getApproveUnit() {
        return this.approveUnit;
    }

    public PwbUnitApply approveUnit(String approveUnit) {
        this.setApproveUnit(approveUnit);
        return this;
    }

    public void setApproveUnit(String approveUnit) {
        this.approveUnit = approveUnit;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public PwbUnitApply updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public PwbUnitApply updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public PwbUnitApply createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public PwbUnitApply createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PwbUnitApply)) {
            return false;
        }
        return id != null && id.equals(((PwbUnitApply) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwbUnitApply{" +
            "id=" + getId() +
            ", unitId='" + getUnitId() + "'" +
            ", applyTime='" + getApplyTime() + "'" +
            ", approveTime='" + getApproveTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", moduleType='" + getModuleType() + "'" +
            ", businessType='" + getBusinessType() + "'" +
            ", approveUnit='" + getApproveUnit() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
