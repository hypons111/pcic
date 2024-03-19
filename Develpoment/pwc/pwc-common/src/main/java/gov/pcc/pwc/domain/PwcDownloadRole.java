package gov.pcc.pwc.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

/**
 * A PwcDownloadRole.
 */
@Entity
@Table(name = "pwc_download_role")
public class PwcDownloadRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long proDownloadRoleId;

    /**
     * type:bigint
     */
    @Column(name = "pro_Download_id")
    private Long proDownloadId;

    /**
     * type:varchar
     */
    @Column(name = "role")
    private String role;

    /**
     * type:datetime
     */
    @Column(name = "update_time")
    private Instant updateTime;

    /**
     * type:nvarchar(20)
     */
    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    /**
     * type:datetime
     */
    @Column(name = "create_time")
    private Instant createTime;

    /**
     * type:nvarchar(20)
     */
    @Size(max = 20)
    @Column(name = "create_user", length = 20)
    private String createUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getProDownloadRoleId() {
        return proDownloadRoleId;
    }

    public void setProDownloadRoleId(Long proDownloadRoleId) {
        this.proDownloadRoleId = proDownloadRoleId;
    }

    public PwcDownloadRole proDownloadRoleId(Long proDownloadRoleId) {
        this.proDownloadRoleId = proDownloadRoleId;
        return this;
    }

    public Long getProDownloadId() {
        return this.proDownloadId;
    }

    public PwcDownloadRole proDownloadId(Long proDownloadId) {
        this.proDownloadId = proDownloadId;
        return this;
    }

    public void setProDownloadId(Long proDownloadId) {
        this.proDownloadId = proDownloadId;
    }

    public String getRole() {
        return this.role;
    }

    public PwcDownloadRole role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public PwcDownloadRole updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public PwcDownloadRole updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public PwcDownloadRole createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public PwcDownloadRole createUser(String createUser) {
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
        if (!(o instanceof PwcDownloadRole)) {
            return false;
        }
        return proDownloadRoleId != null && proDownloadRoleId.equals(((PwcDownloadRole) o).proDownloadRoleId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwcDownloadRole{" +
            "proDownloadRoleId=" + getProDownloadRoleId() +
            ", proDownloadId=" + getProDownloadId() +
            ", role='" + getRole() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            "}";
    }
}
