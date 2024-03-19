package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A AdmRole.
 */
@Entity
@Table(name = "adm_role")
public class AdmRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "role_id", length = 20, nullable = false)
    private String roleId;

    /**
     * type: nvarchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "role_name", length = 20, nullable = false)
    private String roleName;

    /**
     * type: nvarchar(500)
     */
    @NotNull
    @Size(max = 500)
    @Column(name = "role_descript", length = 500, nullable = false)
    private String roleDescript;

    /**
     * type: varchar(10)
     */
    @NotNull
    @Size(max = 10)
    @Column(name = "business_category", length = 10, nullable = false)
    private String businessCategory;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    /**
     * type: datetime
     */
    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getRoleId() {
        return this.roleId;
    }

    public AdmRole roleId(String roleId) {
        this.setRoleId(roleId);
        return this;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public AdmRole roleName(String roleName) {
        this.setRoleName(roleName);
        return this;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescript() {
        return this.roleDescript;
    }

    public AdmRole roleDescript(String roleDescript) {
        this.setRoleDescript(roleDescript);
        return this;
    }

    public void setRoleDescript(String roleDescript) {
        this.roleDescript = roleDescript;
    }

    public String getBusinessCategory() {
        return this.businessCategory;
    }

    public AdmRole businessCategory(String businessCategory) {
        this.setBusinessCategory(businessCategory);
        return this;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public AdmRole updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public AdmRole updateTime(Instant updateTime) {
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
        if (!(o instanceof AdmRole)) {
            return false;
        }
        return roleId != null && roleId.equals(((AdmRole) o).roleId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmRole{" +
            "roleId=" + getRoleId() +
            ", roleName='" + getRoleName() + "'" +
            ", roleDescript='" + getRoleDescript() + "'" +
            ", businessCategory='" + getBusinessCategory() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
