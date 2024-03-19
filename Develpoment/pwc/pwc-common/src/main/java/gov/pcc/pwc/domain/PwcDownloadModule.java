package gov.pcc.pwc.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

/**
 * A PwcDownloadModule.
 */
@Entity
@Table(name = "pwc_download_module")
public class PwcDownloadModule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long proDownloadModuleId;

    /**
     * type:bigint
     */
    @Column(name = "pro_Download_id")
    private Long proDownloadId;

    /**
     * type:varchar
     */
    @Column(name = "module")
    private String module;

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
    public Long getProDownloadModuleId() {
        return proDownloadModuleId;
    }

    public void setProDownloadModuleId(Long proDownloadModuleId) {
        this.proDownloadModuleId = proDownloadModuleId;
    }

    public PwcDownloadModule proDownloadModuleId(Long proDownloadModuleId) {
        this.proDownloadModuleId = proDownloadModuleId;
        return this;
    }

    public Long getProDownloadId() {
        return this.proDownloadId;
    }

    public PwcDownloadModule proDownloadId(Long proDownloadId) {
        this.proDownloadId = proDownloadId;
        return this;
    }

    public void setProDownloadId(Long proDownloadId) {
        this.proDownloadId = proDownloadId;
    }

    public String getModule() {
        return this.module;
    }

    public PwcDownloadModule module(String module) {
        this.module = module;
        return this;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public PwcDownloadModule updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public PwcDownloadModule updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public PwcDownloadModule createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public PwcDownloadModule createUser(String createUser) {
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
        if (!(o instanceof PwcDownloadModule)) {
            return false;
        }
        return proDownloadModuleId != null && proDownloadModuleId.equals(((PwcDownloadModule) o).proDownloadModuleId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwcDownloadModule{" +
            "proDownloadModuleId=" + getProDownloadModuleId() +
            ", proDownloadId=" + getProDownloadId() +
            ", module='" + getModule() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            "}";
    }
}
