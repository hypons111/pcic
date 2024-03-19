package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * A PwcInformationRole.
 */
@Entity
@Table(name = "pwc_information_role")
public class PwcInformationRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long proInformationRoleId;

    /**
     * 訊息資料序號
     */
    @Column(name = "pro_information_id")
    private Long proInformationId;

    /**
     * 角色別
     */
    @Column(name = "role")
    private String role;

    /**
     * 更新時間
     */
    @Column(name = "update_time")
    private Instant updateTime;

    /**
     * 更新人員
     */
    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    /**
     * 建立時間
     */
    @Column(name = "create_time")
    private Instant createTime;

    /**
     * 建立人員
     */
    @Size(max = 20)
    @Column(name = "create_user", length = 20)
    private String createUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getProInformationRoleId() {
        return proInformationRoleId;
    }

    public void setProInformationRoleId(Long proInformationRoleId) {
        this.proInformationRoleId = proInformationRoleId;
    }

    public PwcInformationRole proInformationRoleId(Long proInformationRoleId) {
        this.proInformationRoleId = proInformationRoleId;
        return this;
    }

    public Long getProInformationId() {
        return this.proInformationId;
    }

    public PwcInformationRole proInformationId(Long proInformationId) {
        this.proInformationId = proInformationId;
        return this;
    }

    public void setProInformationId(Long proInformationId) {
        this.proInformationId = proInformationId;
    }

    public String getRole() {
        return this.role;
    }

    public PwcInformationRole role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public PwcInformationRole updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public PwcInformationRole updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public PwcInformationRole createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public PwcInformationRole createUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PwcInformationRole)) {
            return false;
        }
        return proInformationRoleId != null && proInformationRoleId.equals(((PwcInformationRole) o).proInformationRoleId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwcInformationRole{" +
            "proInformationRoleId=" + getProInformationRoleId() +
            ", proInformationId=" + getProInformationId() +
            ", role='" + getRole() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            "}";
    }
}
