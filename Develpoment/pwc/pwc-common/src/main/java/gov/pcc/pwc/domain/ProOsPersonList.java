package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ProOsPersonList.
 */
@Entity
@Table(name = "pro_os_person_list")
public class ProOsPersonList implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "pro_os_person_list_id", nullable = false)
    private Long proOsPersonListId;

    @NotNull
    @Column(name = "pro_org_suggestion_id", nullable = false)
    private Long proOrgSuggestionId;

    @Size(max = 1)
    @Column(name = "person_type", length = 1)
    private String personType;

    @Size(max = 20)
    @Column(name = "person_name", length = 20)
    private String personName;

    @Size(max = 20)
    @Column(name = "person_title", length = 20)
    private String personTitle;

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

    public Long getProOsPersonListId() {
        return this.proOsPersonListId;
    }

    public ProOsPersonList proOsPersonListId(Long proOsPersonListId) {
        this.setProOsPersonListId(proOsPersonListId);
        return this;
    }

    public void setProOsPersonListId(Long proOsPersonListId) {
        this.proOsPersonListId = proOsPersonListId;
    }

    public Long getProOrgSuggestionId() {
        return this.proOrgSuggestionId;
    }

    public ProOsPersonList proOrgSuggestionId(Long proOrgSuggestionId) {
        this.setProOrgSuggestionId(proOrgSuggestionId);
        return this;
    }

    public void setProOrgSuggestionId(Long proOrgSuggestionId) {
        this.proOrgSuggestionId = proOrgSuggestionId;
    }

    public String getPersonType() {
        return this.personType;
    }

    public ProOsPersonList personType(String personType) {
        this.setPersonType(personType);
        return this;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getPersonName() {
        return this.personName;
    }

    public ProOsPersonList personName(String personName) {
        this.setPersonName(personName);
        return this;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonTitle() {
        return this.personTitle;
    }

    public ProOsPersonList personTitle(String personTitle) {
        this.setPersonTitle(personTitle);
        return this;
    }

    public void setPersonTitle(String personTitle) {
        this.personTitle = personTitle;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public ProOsPersonList updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public ProOsPersonList updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public ProOsPersonList createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public ProOsPersonList createTime(Instant createTime) {
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
        if (!(o instanceof ProOsPersonList)) {
            return false;
        }
        return proOsPersonListId != null && proOsPersonListId.equals(((ProOsPersonList) o).proOsPersonListId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProOsPersonList{" +
            "proOsPersonListId=" + getProOsPersonListId() +
            ", proOrgSuggestionId=" + getProOrgSuggestionId() +
            ", personType='" + getPersonType() + "'" +
            ", personName='" + getPersonName() + "'" +
            ", personTitle='" + getPersonTitle() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
