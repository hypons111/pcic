package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A PwbRoleApply.
 */
@Entity
@Table(name = "pwb_role_apply")
public class PwbRoleApply implements Serializable {

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
    @Column(name = "user_id", length = 20, nullable = false)
    private String userId;

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

    public PwbRoleApply id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public PwbRoleApply userId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getApplyTime() {
        return this.applyTime;
    }

    public PwbRoleApply applyTime(Instant applyTime) {
        this.setApplyTime(applyTime);
        return this;
    }

    public void setApplyTime(Instant applyTime) {
        this.applyTime = applyTime;
    }

    public Instant getApproveTime() {
        return this.approveTime;
    }

    public PwbRoleApply approveTime(Instant approveTime) {
        this.setApproveTime(approveTime);
        return this;
    }

    public void setApproveTime(Instant approveTime) {
        this.approveTime = approveTime;
    }

    public String getStatus() {
        return this.status;
    }

    public PwbRoleApply status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModuleType() {
        return this.moduleType;
    }

    public PwbRoleApply moduleType(String moduleType) {
        this.setModuleType(moduleType);
        return this;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public PwbRoleApply updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public PwbRoleApply updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public PwbRoleApply createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public PwbRoleApply createTime(Instant createTime) {
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
        if (!(o instanceof PwbRoleApply)) {
            return false;
        }
        return id != null && id.equals(((PwbRoleApply) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwbRoleApply{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", applyTime='" + getApplyTime() + "'" +
            ", approveTime='" + getApproveTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", moduleType='" + getModuleType() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
