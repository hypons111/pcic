package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.ProNoCommunicateList} entity.
 */
public class ProNoCommunicateListDTO implements Serializable {

    @NotNull
    private Long proNoCommunicateListId;

    @NotNull
    private Long proOrgSuggestionId;

    @Size(max = 1)
    private String noCommunicateType;

    @Size(max = 20)
    private String updateUser;

    private Instant updateTime;

    @Size(max = 20)
    private String createUser;

    private Instant createTime;

    public Long getProNoCommunicateListId() {
        return proNoCommunicateListId;
    }

    public void setProNoCommunicateListId(Long proNoCommunicateListId) {
        this.proNoCommunicateListId = proNoCommunicateListId;
    }

    public Long getProOrgSuggestionId() {
        return proOrgSuggestionId;
    }

    public void setProOrgSuggestionId(Long proOrgSuggestionId) {
        this.proOrgSuggestionId = proOrgSuggestionId;
    }

    public String getNoCommunicateType() {
        return noCommunicateType;
    }

    public void setNoCommunicateType(String noCommunicateType) {
        this.noCommunicateType = noCommunicateType;
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
        if (!(o instanceof ProNoCommunicateListDTO)) {
            return false;
        }

        ProNoCommunicateListDTO proNoCommunicateListDTO = (ProNoCommunicateListDTO) o;
        if (this.proNoCommunicateListId == null) {
            return false;
        }
        return Objects.equals(this.proNoCommunicateListId, proNoCommunicateListDTO.proNoCommunicateListId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.proNoCommunicateListId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProNoCommunicateListDTO{" +
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
