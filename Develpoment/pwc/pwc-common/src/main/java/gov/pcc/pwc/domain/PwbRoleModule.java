package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A PwbRoleModule.
 */
@Entity
@Table(name = "pwb_role_module")
public class PwbRoleModule implements Serializable {

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
     * type: VARCHAR(1)
     */
    @Size(max = 1)
    @Column(name = "role_type", length = 1)
    private String roleType;

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
    @NotNull
    @Size(max = 20)
    @Column(name = "role_id", length = 20, nullable = false)
    private String roleId;

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
    @Column(name = "approve_level", length = 20)
    private String approveLevel;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PwbRoleModule id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleType() {
        return this.roleType;
    }

    public PwbRoleModule roleType(String roleType) {
        this.setRoleType(roleType);
        return this;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getModuleType() {
        return this.moduleType;
    }

    public PwbRoleModule moduleType(String moduleType) {
        this.setModuleType(moduleType);
        return this;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public PwbRoleModule roleId(String roleId) {
        this.setRoleId(roleId);
        return this;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public PwbRoleModule updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public PwbRoleModule updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public PwbRoleModule createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public PwbRoleModule createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public PwbRoleModule businessType(String businessType) {
        this.setBusinessType(businessType);
        return this;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getApproveLevel() {
        return this.approveLevel;
    }

    public PwbRoleModule approveLevel(String approveLevel) {
        this.setApproveLevel(approveLevel);
        return this;
    }

    public void setApproveLevel(String approveLevel) {
        this.approveLevel = approveLevel;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PwbRoleModule)) {
            return false;
        }
        return id != null && id.equals(((PwbRoleModule) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwbRoleModule{" +
            "id=" + getId() +
            ", roleType='" + getRoleType() + "'" +
            ", moduleType='" + getModuleType() + "'" +
            ", roleId='" + getRoleId() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", businessType='" + getBusinessType() + "'" +
            ", approveLevel='" + getApproveLevel() + "'" +
            "}";
    }
}
