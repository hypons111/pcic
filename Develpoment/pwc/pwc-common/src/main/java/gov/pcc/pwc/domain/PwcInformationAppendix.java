package gov.pcc.pwc.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

/**
 * A PwcInformationAppendix.
 */
@Entity
@Table(name = "pwc_information_appendix")
public class PwcInformationAppendix implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long proInformationAppendixId;

    /**
     * type:bigint
     */
    @Column(name = "pro_information_id")
    private Long proInformationId;

    /**
     * type:varchar(1)
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
    public Long getProInformationAppendixId() {
        return proInformationAppendixId;
    }

    public void setProInformationAppendixId(Long proInformationAppendixId) {
        this.proInformationAppendixId = proInformationAppendixId;
    }

    public PwcInformationAppendix proInformationAppendixId(Long proInformationAppendixId) {
        this.proInformationAppendixId = proInformationAppendixId;
        return this;
    }

    public Long getProInformationId() {
        return this.proInformationId;
    }

    public PwcInformationAppendix proInformationId(Long proInformationId) {
        this.proInformationId = proInformationId;
        return this;
    }

    public void setProInformationId(Long proInformationId) {
        this.proInformationId = proInformationId;
    }

    public String getAttributes() {
        return this.attributes;
    }

    public PwcInformationAppendix attributes(String attributes) {
        this.attributes = attributes;
        return this;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getText() {
        return this.text;
    }

    public PwcInformationAppendix text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAppendix() {
        return this.appendix;
    }

    public PwcInformationAppendix appendix(String appendix) {
        this.appendix = appendix;
        return this;
    }

    public void setAppendix(String appendix) {
        this.appendix = appendix;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public PwcInformationAppendix updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public PwcInformationAppendix updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public PwcInformationAppendix createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public PwcInformationAppendix createUser(String createUser) {
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
        if (!(o instanceof PwcInformationAppendix)) {
            return false;
        }
        return proInformationAppendixId != null && proInformationAppendixId.equals(((PwcInformationAppendix) o).proInformationAppendixId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwcInformationAppendix{" +
            "proInformationAppendixId=" + getProInformationAppendixId() +
            ", proInformationId=" + getProInformationId() +
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
