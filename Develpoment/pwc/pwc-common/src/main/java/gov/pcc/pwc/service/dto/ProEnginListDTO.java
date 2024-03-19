package gov.pcc.pwc.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.ProEnginList} entity.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ProEnginListDTO implements Serializable {

    @NotNull
    private Long proEnginListId;

    @Size(max = 10)
    private String proInnovativeProductId;

    @Size(max = 2)
    private String enginType;

    @Size(max = 20)
    private String updateUser;

    private Instant updateTime;

    @Size(max = 20)
    private String createUser;

    private Instant createTime;

    public Long getProEnginListId() {
        return proEnginListId;
    }

    public void setProEnginListId(Long proEnginListId) {
        this.proEnginListId = proEnginListId;
    }

    public String getProInnovativeProductId() {
        return proInnovativeProductId;
    }

    public void setProInnovativeProductId(String proInnovativeProductId) {
        this.proInnovativeProductId = proInnovativeProductId;
    }

    public String getEnginType() {
        return enginType;
    }

    public void setEnginType(String enginType) {
        this.enginType = enginType;
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
        if (!(o instanceof ProEnginListDTO)) {
            return false;
        }

        ProEnginListDTO proEnginListDTO = (ProEnginListDTO) o;
        if (this.proEnginListId == null) {
            return false;
        }
        return Objects.equals(this.proEnginListId, proEnginListDTO.proEnginListId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.proEnginListId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProEnginListDTO{" +
            "proEnginListId=" + getProEnginListId() +
            ", proInnovativeProductId='" + getProInnovativeProductId() + "'" +
            ", enginType='" + getEnginType() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
