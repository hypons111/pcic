package gov.pcc.pwc.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

/**
 * A PwcDownloadAppendix.
 */
@Entity
@Table(name = "pwc_download_appendix")
public class PwcDownloadAppendix implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long proDownloadAppendixId;

    /**
     * type:bigint
     */
    @Column(name = "pro_Download_id")
    private Long proDownloadId;

    /**
     * type:varchar
     */
    @Column(name = "attributes")
    private String attributes;

    /**
     * type:nvarchar(1000)
     */
    @Size(max = 1000)
    @Column(name = "text", length = 1000)
    private String text;

    /**
     * type:varchar
     */
    @Column(name = "appendix")
    private String appendix;

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
    public Long getProDownloadAppendixId() {
        return proDownloadAppendixId;
    }

    public void setProDownloadAppendixId(Long proDownloadAppendixId) {
        this.proDownloadAppendixId = proDownloadAppendixId;
    }

    public PwcDownloadAppendix proDownloadAppendixId(Long proDownloadAppendixId) {
        this.proDownloadAppendixId = proDownloadAppendixId;
        return this;
    }

    public Long getProDownloadId() {
        return this.proDownloadId;
    }

    public PwcDownloadAppendix proDownloadId(Long proDownloadId) {
        this.proDownloadId = proDownloadId;
        return this;
    }

    public void setProDownloadId(Long proDownloadId) {
        this.proDownloadId = proDownloadId;
    }

    public String getAttributes() {
        return this.attributes;
    }

    public PwcDownloadAppendix attributes(String attributes) {
        this.attributes = attributes;
        return this;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getText() {
        return this.text;
    }

    public PwcDownloadAppendix text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAppendix() {
        return this.appendix;
    }

    public PwcDownloadAppendix appendix(String appendix) {
        this.appendix = appendix;
        return this;
    }

    public void setAppendix(String appendix) {
        this.appendix = appendix;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public PwcDownloadAppendix updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public PwcDownloadAppendix updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public PwcDownloadAppendix createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public PwcDownloadAppendix createUser(String createUser) {
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
        if (!(o instanceof PwcDownloadAppendix)) {
            return false;
        }
        return proDownloadAppendixId != null && proDownloadAppendixId.equals(((PwcDownloadAppendix) o).proDownloadAppendixId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwcDownloadAppendix{" +
            "proDownloadAppendixId=" + getProDownloadAppendixId() +
            ", proDownloadId=" + getProDownloadId() +
            ", attributes='" + getAttributes() + "'" +
            ", text='" + getText() + "'" +
            ", appendix='" + getAppendix() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            "}";
    }
}
