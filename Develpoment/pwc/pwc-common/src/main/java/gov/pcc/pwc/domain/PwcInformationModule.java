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
 * A PwcInformationModule.
 */
@Entity
@Table(name = "pwc_information_module")
public class PwcInformationModule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long proInformationModuleId;

    /**
     * 訊息資料序號
     */
    @Column(name = "pro_information_id")
    private Long proInformationId;

    /**
     * 模組別
     */
    @Column(name = "module")
    private String module;

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
    public Long getProInformationModuleId() {
        return proInformationModuleId;
    }

    public void setProInformationModuleId(Long proInformationModuleId) {
        this.proInformationModuleId = proInformationModuleId;
    }

    public PwcInformationModule proInformationModuleId(Long proInformationModuleId) {
        this.proInformationModuleId = proInformationModuleId;
        return this;
    }

    public Long getProInformationId() {
        return this.proInformationId;
    }

    public PwcInformationModule proInformationId(Long proInformationId) {
        this.proInformationId = proInformationId;
        return this;
    }

    public void setProInformationId(Long proInformationId) {
        this.proInformationId = proInformationId;
    }

    public String getModule() {
        return this.module;
    }

    public PwcInformationModule module(String module) {
        this.module = module;
        return this;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public PwcInformationModule updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public PwcInformationModule updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public PwcInformationModule createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public PwcInformationModule createUser(String createUser) {
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
        if (!(o instanceof PwcInformationModule)) {
            return false;
        }
        return proInformationModuleId != null && proInformationModuleId.equals(((PwcInformationModule) o).proInformationModuleId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwcInformationModule{" +
            "proInformationModuleId=" + getProInformationModuleId() +
            ", proInformationId=" + getProInformationId() +
            ", module='" + getModule() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            "}";
    }
}
