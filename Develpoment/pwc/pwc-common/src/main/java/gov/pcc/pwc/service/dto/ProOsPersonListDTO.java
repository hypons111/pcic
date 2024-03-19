package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.ProOsPersonList} entity.
 */
public class ProOsPersonListDTO implements Serializable {

    @NotNull
    private Long proOsPersonListId;

    @NotNull
    private Long proOrgSuggestionId;

    @Size(max = 1)
    private String personType;

    @Size(max = 20)
    private String personName;

    @Size(max = 20)
    private String personTitle;

    @Size(max = 20)
    private String updateUser;

    private Instant updateTime;

    @Size(max = 20)
    private String createUser;

    private Instant createTime;

    public Long getProOsPersonListId() {
        return proOsPersonListId;
    }

    public void setProOsPersonListId(Long proOsPersonListId) {
        this.proOsPersonListId = proOsPersonListId;
    }

    public Long getProOrgSuggestionId() {
        return proOrgSuggestionId;
    }

    public void setProOrgSuggestionId(Long proOrgSuggestionId) {
        this.proOrgSuggestionId = proOrgSuggestionId;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonTitle() {
        return personTitle;
    }

    public void setPersonTitle(String personTitle) {
        this.personTitle = personTitle;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProOsPersonListDTO)) {
            return false;
        }

        ProOsPersonListDTO proOsPersonListDTO = (ProOsPersonListDTO) o;
        if (this.proOsPersonListId == null) {
            return false;
        }
        return Objects.equals(this.proOsPersonListId, proOsPersonListDTO.proOsPersonListId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.proOsPersonListId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProOsPersonListDTO{" +
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
