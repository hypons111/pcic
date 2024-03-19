package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ProNoCommunicateList.
 */
@Entity
@Table(name = "pro_no_communicate_list")
public class ProNoCommunicateList implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "pro_no_communicate_list_id", nullable = false)
    private Long proNoCommunicateListId;

    @NotNull
    @Column(name = "pro_org_suggestion_id", nullable = false)
    private Long proOrgSuggestionId;

    @Size(max = 1)
    @Column(name = "no_communicate_type", length = 1)
    private String noCommunicateType;

    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    @Column(name = "update_time")
    private Instant updateTime;

    @Size(max = 20)
    @Column(name = "create_user", length = 20)
    private String createUser;

    @Column(name = "create_time")
    private Instant createTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getProNoCommunicateListId() {
        return this.proNoCommunicateListId;
    }

    public ProNoCommunicateList proNoCommunicateListId(Long proNoCommunicateListId) {
        this.setProNoCommunicateListId(proNoCommunicateListId);
        return this;
    }

    public void setProNoCommunicateListId(Long proNoCommunicateListId) {
        this.proNoCommunicateListId = proNoCommunicateListId;
    }

    public Long getProOrgSuggestionId() {
        return this.proOrgSuggestionId;
    }

    public ProNoCommunicateList proOrgSuggestionId(Long proOrgSuggestionId) {
        this.setProOrgSuggestionId(proOrgSuggestionId);
        return this;
    }

    public void setProOrgSuggestionId(Long proOrgSuggestionId) {
        this.proOrgSuggestionId = proOrgSuggestionId;
    }

    public String getNoCommunicateType() {
        return this.noCommunicateType;
    }

    public ProNoCommunicateList noCommunicateType(String noCommunicateType) {
        this.setNoCommunicateType(noCommunicateType);
        return this;
    }

    public void setNoCommunicateType(String noCommunicateType) {
        this.noCommunicateType = noCommunicateType;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public ProNoCommunicateList updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public ProNoCommunicateList updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public ProNoCommunicateList createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public ProNoCommunicateList createTime(Instant createTime) {
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
        if (!(o instanceof ProNoCommunicateList)) {
            return false;
        }
        return proNoCommunicateListId != null && proNoCommunicateListId.equals(((ProNoCommunicateList) o).proNoCommunicateListId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProNoCommunicateList{" +
            "proNoCommunicateListId=" + getProNoCommunicateListId() +
            ", proOrgSuggestionId=" + getProOrgSuggestionId() +
            ", noCommunicateType='" + getNoCommunicateType() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
